package ru.country.utilites;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.country.dao.CityDaoImpl;
import ru.country.dao.CountryDaoImpl;
import ru.country.entity.City;
import ru.country.entity.Country;

import java.util.ArrayList;
import java.util.List;

public class FetchingData {

    public List<City> fetchData(SessionFactory sessionFactory) {
        CityDaoImpl cityDAO = new CityDaoImpl(sessionFactory);
        CountryDaoImpl countryDAO = new CountryDaoImpl(sessionFactory);

        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDAO.getAll();
            int totalCount = cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}

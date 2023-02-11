package ru.country.testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.country.dao.CityDaoImpl;
import ru.country.entity.City;
import ru.country.entity.CountryLanguage;

import java.util.List;
import java.util.Set;

public class TestingMysql {

    public void testMysqlData(List<Integer> ids, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = new CityDaoImpl(sessionFactory).getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}

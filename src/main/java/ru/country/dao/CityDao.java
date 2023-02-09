package ru.country.dao;

import org.hibernate.query.Query;
import ru.country.entity.City;

import java.util.List;

public interface CityDao {

    List<City> getItems(int offset, int limit);

    int getTotalCount();

    City getById(Integer id);
}

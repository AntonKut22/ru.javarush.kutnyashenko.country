package ru.country.dao;

import ru.country.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getAll();
}

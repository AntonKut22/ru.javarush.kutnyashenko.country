package ru.country.utilites;

import ru.country.entity.City;
import ru.country.entity.Country;
import ru.country.entity.CountryLanguage;
import ru.country.redis.CityCountry;
import ru.country.redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Convertor {

    public List<CityCountry> transformData(List<City> cities) {
        return cities.stream().map(this::getCityCountry).toList();
    }

    private CityCountry getCityCountry(City city) {
        CityCountry res = new CityCountry();
        Country country = city.getCountry();
        res.setId(city.getId());
        res.setName(city.getName());
        res.setPopulation(city.getPopulation());
        res.setDistrict(city.getDistrict());
        res.setAlternativeCountryCode(country.getCode2());
        res.setContinent(country.getContinent());
        res.setCountryCode(country.getCode());
        res.setCountryName(country.getName());
        res.setCountryPopulation(country.getPopulation());
        res.setCountryRegion(country.getRegion());
        res.setCountrySurfaceArea(country.getSurfaceArea());
        Set<CountryLanguage> countryLanguages = country.getLanguages();
        Set<Language> languages = getLanguages(countryLanguages);
        res.setLanguages(languages);
        return res;
    }

    private Set<Language> getLanguages(Set<CountryLanguage> countryLanguages) {
        return countryLanguages.stream().map(Convertor::getLanguage).collect(Collectors.toSet());
    }

    private static Language getLanguage(CountryLanguage cl) {
        Language language = new Language();
        language.setLanguage(cl.getLanguage());
        language.setIsOfficial(cl.getIsOfficial());
        language.setPercentage(cl.getPercentage());
        return language;
    }
}

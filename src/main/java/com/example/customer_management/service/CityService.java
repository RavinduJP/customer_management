package com.example.customer_management.service;

import com.example.customer_management.entity.City;

import java.util.Optional;

public interface CityService {

    City findOrCreateCity(String cityName, String CountryName);
    Optional<City> findByName(String cityName);
}

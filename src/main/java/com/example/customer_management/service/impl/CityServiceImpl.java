package com.example.customer_management.service.impl;

import com.example.customer_management.entity.City;
import com.example.customer_management.entity.Country;
import com.example.customer_management.repository.CityRepository;
import com.example.customer_management.service.CityService;
import com.example.customer_management.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;

    @Override
    public City findOrCreateCity(String cityName, String countryName) {
        return cityRepository.findByName(cityName)
                .orElseGet(() -> {
                    Country country = countryService.findOrCreateCountry(countryName);
                    City newCity = new City();
                    newCity.setName(cityName);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });

    }

    @Override
    public Optional<City> findByName(String cityName) {
        return cityRepository.findByName(cityName);
    }
}

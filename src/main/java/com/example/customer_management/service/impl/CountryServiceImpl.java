package com.example.customer_management.service.impl;

import com.example.customer_management.entity.Country;
import com.example.customer_management.repository.CountryRepository;
import com.example.customer_management.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public Country findOrCreateCountry(String countryName) {
        return countryRepository.findByName(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setName(countryName);
                    return countryRepository.save(newCountry);
                });
    }

    @Override
    public Optional<Country> findByName(String countryName) {
        return countryRepository.findByName(countryName);
    }
}

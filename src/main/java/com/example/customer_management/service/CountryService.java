package com.example.customer_management.service;

import com.example.customer_management.entity.Country;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CountryService {
    Country findOrCreateCountry(String countryName);
    Optional<Country> findByName(String countryName);
}

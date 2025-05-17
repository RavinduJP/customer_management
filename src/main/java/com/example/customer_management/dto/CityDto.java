package com.example.customer_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private Long id;
    private String name;
    private Long countryId;
    private String countryName;
}

package com.example.customer_management.dto;

import com.example.customer_management.entity.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;

    @NotBlank(message = "Address line 1 is mandatory")
    private String addressLine1;

    private String addressLine2;

    private City cityId;
    @NotNull(message = "City is mandatory")
    private String cityName;
    private String countryName;
}

package com.example.customer_management.dto;

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

    @NotNull(message = "City is mandatory")
    private Long cityId;

    private String cityName;
    private String countryName;
}

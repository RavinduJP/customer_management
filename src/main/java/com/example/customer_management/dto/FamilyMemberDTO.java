package com.example.customer_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberDTO {

    private Long id;

    @NotNull(message = "Customer ID is mandatory")
    private Long customerId;

    private String customerName;

    @NotBlank(message = "Relation type is mandatory")
    private String relationType;
}

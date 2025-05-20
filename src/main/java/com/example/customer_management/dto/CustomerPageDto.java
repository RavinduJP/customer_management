package com.example.customer_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPageDto {
    private List<CustomerDto> customers;

    private int totalPages;

    private long totalElements;

    private int currentPage;
}

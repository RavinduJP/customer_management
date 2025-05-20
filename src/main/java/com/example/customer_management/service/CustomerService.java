package com.example.customer_management.service;

import com.example.customer_management.dto.CustomerDto;
import com.example.customer_management.dto.response.BaseResponse;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {
    BaseResponse<HashMap <String, Object>> createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id,CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    void deleteCustomer(Long id);


}

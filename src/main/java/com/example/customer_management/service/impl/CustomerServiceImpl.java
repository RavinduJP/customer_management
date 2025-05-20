package com.example.customer_management.service.impl;

import com.example.customer_management.dto.CustomerDto;
import com.example.customer_management.dto.response.BaseResponse;
import com.example.customer_management.entity.Customer;
import com.example.customer_management.repository.CustomerRepository;
import com.example.customer_management.service.CustomerService;
import com.example.customer_management.utils.ResponseCodeUtils;
import com.example.customer_management.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.LongStream.builder;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public BaseResponse<HashMap<String, Object>> createCustomer(CustomerDto customerDto) {
        log.info("Creating new customer with NIC: {}", customerDto.getNicNumber());
        try {
            if (customerRepository.existsByNicNumber(customerDto.getNicNumber())) {
                return BaseResponse.<HashMap<String, Object>>builder()
                        .code(ResponseCodeUtils.FAILED_CODE)
                        .title(ResponseUtils.FAILED)
                        .message("User already exists with email: " + customerDto.getNicNumber())
                        .build();
            }

            Customer newCustomer =Customer.builder()
                    .name(customerDto.getName())
                    .dateOfBirth(customerDto.getDateOfBirth())
                    .nicNumber(customerDto.getNicNumber())
                    .phoneNumbers(customerDto.getPhoneNumbers())
                    .relations(customerDto.getFamilyMembers())
                    .build();

            return null;
        } catch (Exception e) {
            return BaseResponse.<HashMap<String, Object>>builder()
                    .code(ResponseCodeUtils.INTERNAL_SERVER_ERROR_CODE)
                    .title(ResponseUtils.INTERNAL_SERVER_ERROR)
                    .message("Error occurred while saving new user details")
                    .build();

        }
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}

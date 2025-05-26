package com.example.customer_management.controller;

import com.example.customer_management.dto.CustomerDto;
import com.example.customer_management.dto.request.CreateCustomerRequest;
import com.example.customer_management.dto.response.BaseResponse;
import com.example.customer_management.dto.response.DefaultResponse;
import com.example.customer_management.service.impl.CustomerServiceImpl;
import com.example.customer_management.utils.ResponseCodeUtils;
import com.example.customer_management.utils.ResponseUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getHello() {
        return "Hello World!";
    }

    @PostMapping("/create_customer")
    public ResponseEntity<DefaultResponse> createCustomers(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        log.info("Customer creation attempted for NIC: {}", createCustomerRequest.getNicNumber());
        try {
            BaseResponse<HashMap<String, Object>> response = customerService.createCustomer(createCustomerRequest);
            if (response.getCode().equals(ResponseCodeUtils.SUCCESS_CODE)) {
                return ResponseEntity.ok(DefaultResponse.success(ResponseUtils.SUCCESS, response.getMessage(), response.getData()));
            } else if (response.getCode().equals(ResponseCodeUtils.INTERNAL_SERVER_ERROR_CODE)) {
                return ResponseEntity.internalServerError().body(DefaultResponse.internalServerError(ResponseCodeUtils.INTERNAL_SERVER_ERROR_CODE, response.getMessage()));
            } else {
                return ResponseEntity.badRequest().body(DefaultResponse.error(ResponseUtils.FAILED, response.getMessage(), response.getData()));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(DefaultResponse.error(ResponseUtils.FAILED, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(DefaultResponse.internalServerError(ResponseCodeUtils.INTERNAL_SERVER_ERROR_CODE, "Unexpected error occurred"));
        }
    }


    @GetMapping("/All_Customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}

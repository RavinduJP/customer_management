package com.example.customer_management.service.impl;

import com.example.customer_management.dto.CustomerDto;
import com.example.customer_management.dto.request.CreateCustomerRequest;
import com.example.customer_management.dto.response.BaseResponse;
import com.example.customer_management.entity.*;
import com.example.customer_management.repository.*;
import com.example.customer_management.service.CityService;
import com.example.customer_management.service.CountryService;
import com.example.customer_management.service.CustomerService;
import com.example.customer_management.utils.ResponseCodeUtils;
import com.example.customer_management.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private final PhoneNumberRepository phoneNumberRepository;
    private final RelationRepository relationRepository;

    @Override
    public BaseResponse<HashMap<String, Object>> createCustomer(CreateCustomerRequest createCustomerRequest) {
        log.info("Creating new customer with NIC: {}", createCustomerRequest.getNicNumber());
        try {
            if (customerRepository.existsByNicNumber(createCustomerRequest.getNicNumber())) {

                return BaseResponse.<HashMap<String, Object>>builder()
                        .code(ResponseCodeUtils.FAILED_CODE)
                        .title(ResponseUtils.FAILED)
                        .message("User already exists with NIC: " + createCustomerRequest.getNicNumber())
                        .build();
            }

            Customer newCustomer = Customer.builder()
                    .name(createCustomerRequest.getName())
                    .dateOfBirth(createCustomerRequest.getDateOfBirth())
                    .nicNumber(createCustomerRequest.getNicNumber())
                    .build();
            Customer savedCustomer = customerRepository.save(newCustomer);

            List<Address> addresses = createCustomerRequest.getAddresses().stream()
                    .map(addressDto -> {
                        Address address = new Address();
                        address.setAddressLine1(addressDto.getAddressLine1());
                        address.setAddressLine2(addressDto.getAddressLine2());

                        log.info("Address: {}, City: {}, Country: {}", address.getAddressLine1(), address.getCity(), address.getCountry());

                        City city = cityService.findOrCreateCity(addressDto.getCity().getName(), addressDto.getCountry().getName());
                        if (city == null || city.getId() == null) {
                            throw new RuntimeException("City could not be created or found");
                        }
                        address.setCity(city);

                        Country country = countryService.findOrCreateCountry(addressDto.getCountry().getName());
                        address.setCountry(country);

                        address.setCustomer(savedCustomer);
                        return address;
                    })
                    .collect(Collectors.toList());
            addressRepository.saveAll(addresses);

            List<PhoneNumber> phoneNumberList = createCustomerRequest.getPhoneNumbers().stream()
                    .map(number -> {
                        PhoneNumber phoneNumber = new PhoneNumber();
                        phoneNumber.setNumber(number);
                        phoneNumber.setCustomer(newCustomer);
                        return phoneNumber;
                    })
                    .collect(Collectors.toList());
            phoneNumberRepository.saveAll(phoneNumberList);
            savedCustomer.setPhoneNumbers(phoneNumberList);

//get the customer relationship
            List<CustomerRelation> relations = createCustomerRequest.getFamilyMembers().stream()
                    .map(familyMemberDTO -> {
                        Customer customerRelation = customerRepository.findByNicNumber(createCustomerRequest.getNicNumber())
                                .orElseThrow(() -> new RuntimeException("Related customer not found with NIC: " + createCustomerRequest.getNicNumber()));
                        return CustomerRelation.builder()
                                .customer(customerRelation)
                                .relation(familyMemberDTO.getCustomerName())
                                .relationType(familyMemberDTO.getRelationType())
                                .build();
                    }).collect(Collectors.toList());
            relationRepository.saveAll(relations);
            savedCustomer.setFamilyMembers(relations);


            HashMap<String, Object> newCustomerObj = new HashMap<>();
            newCustomerObj.put("name", savedCustomer.getName());
            newCustomerObj.put("dateOfBirth", savedCustomer.getDateOfBirth());
            newCustomerObj.put("familyMembers", savedCustomer.getFamilyMembers());
            newCustomerObj.put("phoneNumberList", savedCustomer.getPhoneNumbers());

            return BaseResponse.<HashMap<String, Object>>builder()
                    .code(ResponseCodeUtils.SUCCESS_CODE)
                    .title(ResponseUtils.SUCCESS)
                    .message("User creation successfully")
                    .build();
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

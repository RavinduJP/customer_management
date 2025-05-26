package com.example.customer_management.dto.request;

import com.example.customer_management.dto.FamilyMemberDTO;
import com.example.customer_management.entity.Address;
import com.example.customer_management.entity.CustomerRelation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String nicNumber;
    private List<String> phoneNumbers = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<FamilyMemberDTO> familyMembers = new ArrayList<>();

}

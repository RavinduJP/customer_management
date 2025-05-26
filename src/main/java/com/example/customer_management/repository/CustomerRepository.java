package com.example.customer_management.repository;

import com.example.customer_management.dto.CustomerDto;
import com.example.customer_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByNicNumber(String nicNumber);

    boolean existsByNicNumber(String nicNumber);

//    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.phoneNumbers LEFT JOIN FETCH c.addresses a LEFT JOIN FETCH a.city ct LEFT JOIN FETCH ct.country WHERE c.id = :id")
//    Optional<Customer> findByIdWithDetails(@Param("id") Long id);
//
//    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.relations WHERE c.id = :id")
//    Optional<Customer> findByIdWithFamilyMembers(@Param("id") Long id);
    @Query(value = "SELECT c FROM Customer c LEFT JOIN FETCH c.phoneNumbers LEFT JOIN FETCH c.addresses a LEFT JOIN FETCH a.city ct LEFT JOIN FETCH ct.country")
    public List<CustomerDto> getAllCustomers();
}

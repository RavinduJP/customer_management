package com.example.customer_management.repository;

import com.example.customer_management.entity.CustomerRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<CustomerRelation, Long> {
}

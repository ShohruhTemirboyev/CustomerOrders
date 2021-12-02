package com.example.task.repository;

import com.example.task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findById(Integer custId);
}

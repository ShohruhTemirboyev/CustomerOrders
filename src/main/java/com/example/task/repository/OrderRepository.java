package com.example.task.repository;

import com.example.task.entity.Customer;
import com.example.task.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findById(Integer orderId);
    List<Order> findAllByCust(Customer customer);
    List<Order> findAllByCust_Country(String cust_country);
}

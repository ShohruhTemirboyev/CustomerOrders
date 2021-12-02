package com.example.task.repository;

import com.example.task.entity.Invoice;
import com.example.task.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findAllByInv(Invoice invoice);
}

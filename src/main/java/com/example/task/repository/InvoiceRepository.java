package com.example.task.repository;

import com.example.task.entity.Invoice;
import com.example.task.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository  extends JpaRepository<Invoice,Integer> {
    Optional<Invoice> findById(Integer invoiceId);
    boolean existsByOrd(Order order);
}

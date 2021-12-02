package com.example.task.repository;

import com.example.task.entity.Detail;
import com.example.task.entity.Order;
import com.example.task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail,Integer> {
    List<Detail> findAllByOrd(Order orderId);
    boolean existsByOrd(Order order);
    List<Detail> findAllByPr(Product product);
}

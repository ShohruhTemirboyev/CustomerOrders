package com.example.task;

import com.example.task.entity.Order;
import com.example.task.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);

    }

}

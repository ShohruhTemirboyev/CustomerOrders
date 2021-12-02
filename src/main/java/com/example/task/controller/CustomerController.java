package com.example.task.controller;

import com.example.task.payloat.ApiResponseModel;
import com.example.task.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers_without_orders")
    HttpEntity<?> getCustomerWithoutOrders(){
        ApiResponseModel apiResponseModel=customerService.getCustomersWithoutOrders();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/customers_last_orders")
    HttpEntity<?> getCustomersLastOrders(){
        ApiResponseModel apiResponseModel=customerService.getCustomersLastOrders();
        return ResponseEntity.ok(apiResponseModel);
    }

}

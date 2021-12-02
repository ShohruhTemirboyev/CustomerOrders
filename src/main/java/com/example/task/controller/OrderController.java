package com.example.task.controller;

import com.example.task.payloat.ApiResponsGetOrder;
import com.example.task.payloat.ApiResponseOrder;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ReqOrder;
import com.example.task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    HttpEntity<?> saveOrder(@RequestBody ReqOrder reqOrder){
        ApiResponseOrder apiResponseModel=orderService.addOrder(reqOrder);
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/details")
    HttpEntity<?> getOrder(@RequestParam Integer order_id){
        ApiResponsGetOrder apiResponseModel =orderService.getOrder(order_id);
        return ResponseEntity.ok(apiResponseModel);
    }


}

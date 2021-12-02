package com.example.task.controller;

import com.example.task.entity.Payment;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ReqPayment;
import com.example.task.payloat.ResPayment;
import com.example.task.repository.PaymentRepository;
import com.example.task.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping()
    public HttpEntity<?> savePayment(@RequestBody ReqPayment reqPayment){
        ResPayment resPayment =paymentService.addPayment(reqPayment.getInvoice_id());
        return ResponseEntity.ok(resPayment);
    }

    @GetMapping("/details")
    HttpEntity<?> getPayment(@RequestParam Integer id){
        ApiResponseModel apiResponseModel=paymentService.getPayment(id);
        return ResponseEntity.ok(apiResponseModel);
    }

}

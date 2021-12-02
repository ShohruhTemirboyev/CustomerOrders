package com.example.task.controller;

import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ResWrongDateInvoices;
import com.example.task.service.InvoiceService;
import com.example.task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class InvoicesController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    OrderService orderService;

    @GetMapping("/expired_invoices")
    HttpEntity<?> getExpiredInvoices(){
        ApiResponseModel apiResponseModel=invoiceService.getExpiredInvoices();
        return ResponseEntity.ok(apiResponseModel);
    }
    @GetMapping("/wrong_date_invoices")
    HttpEntity<?> getWrongDateInvoices(){
        ApiResponseModel apiResponseModel=invoiceService.getWrongDateInvoice();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/orders_without_details")
    HttpEntity<?> getOrderWithoutDetails(){
        ApiResponseModel apiResponseModel=orderService.getOrderWithoutDetails();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/overpaid_invoices")
    HttpEntity<?> getOverpaidInvoices(){
        ApiResponseModel apiResponseModel=invoiceService.getOverpaidInvoices();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/high_demand_products")
    HttpEntity<?> getHighDemandProducts(){
        ApiResponseModel apiResponseModel=orderService.getHighDemandProducts();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/bulk_products")
    HttpEntity<?> getBulkProduct(){
        ApiResponseModel apiResponseModel=orderService.getBulkProduct();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/number_of_products_in_year")
    HttpEntity<?> getNumberOfProductsInYear(){
        ApiResponseModel apiResponseModel=orderService.getNumberProductYear();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/orders_without_invoices")
    HttpEntity<?> getOrdersWithoutInvoices(){
        ApiResponseModel apiResponseModel=orderService.getOrdersWithoutInvoices();
        return ResponseEntity.ok(apiResponseModel);
    }

}

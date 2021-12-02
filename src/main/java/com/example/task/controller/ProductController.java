package com.example.task.controller;

import com.example.task.payloat.ApiResponseModel;
import com.example.task.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
     HttpEntity<?> getProductList(){
        ApiResponseModel apiResponseModel=productService.getProductList();
        return ResponseEntity.ok(apiResponseModel);
    }

    @GetMapping("/details")
    HttpEntity<?> getProduct(@RequestParam Integer product_id){
        ApiResponseModel apiResponseModel=productService.getProduct(product_id);
        return ResponseEntity.ok(apiResponseModel);
    }
}

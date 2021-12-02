package com.example.task.controller;

import com.example.task.entity.Category;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.repository.CategoryRepository;
import com.example.task.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    HttpEntity<?> getCategorys(){
     return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping()
    HttpEntity<?> getCategory(@RequestParam Integer product_id){
        ApiResponseModel apiResponseModel =categoryService.getCategory(product_id);
        return ResponseEntity.ok(apiResponseModel);
    }
}

package com.example.task.service;

import com.example.task.entity.Product;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    ProductRepository productRepository;

    public ApiResponseModel getCategory(Integer id){
        ApiResponseModel responseModel=new ApiResponseModel();
        try{
            Optional<Product> product=productRepository.findById(id);
            if (product.isPresent()){
                responseModel.setObject(product.get().getCategory());
                responseModel.setCode(200);
                responseModel.setMessage("success");

            }
            else {
                responseModel.setCode(207);
                responseModel.setMessage("Product did not found");
            }

        }
        catch (Exception exception){
            responseModel.setMessage("Error");
            responseModel.setCode(500);
        }
return responseModel;
    }

}

package com.example.task.service;

import com.example.task.entity.Product;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ApiResponseModel getProduct(Integer id){
        ApiResponseModel responseModel=new ApiResponseModel();
        try{
            Optional<Product> product=productRepository.findById(id);
            if (product.isPresent()){
                responseModel.setObject(product.get());
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
    public ApiResponseModel getProductList(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<Product> productList=productRepository.findAll();
            apiResponseModel.setCode(200);
            apiResponseModel.setMessage("Success");
            apiResponseModel.setObject(productList);


        }
        catch (Exception e){
            apiResponseModel.setMessage("Error");
            apiResponseModel.setCode(500);
        }
        return apiResponseModel;
    }


}

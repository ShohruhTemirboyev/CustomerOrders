package com.example.task.service;

import com.example.task.entity.Customer;
import com.example.task.entity.Order;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ResLastOrder;
import com.example.task.repository.CustomerRepository;
import com.example.task.repository.OrderRepository;
import com.example.task.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public ApiResponseModel getCustomersWithoutOrders(){

        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<Customer> orderList=customerRepository.findAll().stream().map(customer -> getCusWitOrd(customer, CommonUtils.parseTimestamp("2015-12-31"),CommonUtils.parseTimestamp("2017-01-01"))).collect(Collectors.toList());
            apiResponseModel.setCode(200);
            apiResponseModel.setMessage("SUCCESS");
           orderList.removeAll(Collections.singletonList(null));
            apiResponseModel.setObject(orderList);

        }
        catch (Exception e){
            apiResponseModel.setMessage("Error");
            apiResponseModel.setCode(500);
        }
        return apiResponseModel;

    }

    public ApiResponseModel getCustomersLastOrders(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<ResLastOrder> resLastOrders=customerRepository.findAll().stream().map(customer ->getCustLastOrd(customer) ).collect(Collectors.toList());
            apiResponseModel.setMessage("SUCCESS");
            apiResponseModel.setCode(200);
            resLastOrders.removeAll(Collections.singletonList(null));

            apiResponseModel.setObject(resLastOrders);
        }
        catch (Exception exception){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("Error");
        }
        return apiResponseModel;
    }

    public ResLastOrder getCustLastOrd(Customer customer){
        List<Order> orderList=orderRepository.findAllByCust(customer);
        if (orderList.size() == 0){
            return null;
        }
        else {
            ResLastOrder resLastOrder=new ResLastOrder();
            Date maxDate=orderList.stream().map(order -> order.getDate()).max(Date::compareTo).get();
            resLastOrder.setLastOrderDate(maxDate);
            resLastOrder.setId(customer.getId());
            resLastOrder.setName(customer.getName());
            return resLastOrder;
        }
    }

    public Customer getCusWitOrd(Customer customer,Date dateBefore, Date dateAfter){
        List<Order> orderList=orderRepository.findAllByCust(customer);
        for (Order order:orderList) {
            if (order.getDate().after(dateBefore) && order.getDate().before(dateAfter)){
                return null;
            }
        }
        return customer;
    }


}

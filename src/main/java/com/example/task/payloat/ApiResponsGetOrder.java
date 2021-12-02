package com.example.task.payloat;

import com.example.task.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponsGetOrder {

    private Integer code;

    private String message;

    private Order order;

    private List<String> product_name;
}

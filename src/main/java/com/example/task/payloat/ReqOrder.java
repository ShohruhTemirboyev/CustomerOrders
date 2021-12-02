package com.example.task.payloat;

import lombok.Data;

@Data
public class ReqOrder {

    private Integer customer_id;

    private Integer product_id;

    private Integer quantity;

}

package com.example.task.payloat;

import lombok.Data;

import java.sql.Date;

@Data
public class ResOrdersWithoutInvoices {

    private Integer Order_Id;

    private Date date;

    private Double price;

}

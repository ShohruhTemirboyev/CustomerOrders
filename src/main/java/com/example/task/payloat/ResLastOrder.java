package com.example.task.payloat;

import lombok.Data;

import java.sql.Date;

@Data
public class ResLastOrder {

    private Integer Id;
    private String name;
    private Date lastOrderDate;

}

package com.example.task.payloat;

import lombok.Data;

import java.sql.Date;

@Data
public class ResWrongDateInvoices {


    private Integer Invoices_Id;
    private Date Issued;

    private Integer Order_Id;
    private Date Date;
}

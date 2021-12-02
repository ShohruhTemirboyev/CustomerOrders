package com.example.task.service;

import com.example.task.entity.Invoice;
import com.example.task.entity.Payment;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ResOverpaidInvoices;
import com.example.task.payloat.ResWrongDateInvoices;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {


    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public ApiResponseModel getExpiredInvoices(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<Invoice> invoiceList=invoiceRepository.findAll().stream().map(invoice -> getEXIn(invoice)).collect(Collectors.toList());
            apiResponseModel.setCode(200);
            apiResponseModel.setMessage("SUCCESS");
            invoiceList.removeAll(Collections.singletonList(null));
            apiResponseModel.setObject(invoiceList);
        }
        catch (Exception exception){
            apiResponseModel.setMessage("ERROR");
            apiResponseModel.setCode(500);
        }
        return apiResponseModel;
    }

    public ApiResponseModel getWrongDateInvoice(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
          List<ResWrongDateInvoices> invoiceList=invoiceRepository.findAll().stream().map(invoice -> getWrDaIn(invoice)).collect(Collectors.toList());
            apiResponseModel.setMessage("SUCCESS");
            apiResponseModel.setCode(200);
            invoiceList.removeAll(Collections.singletonList(null));
            apiResponseModel.setObject(invoiceList);
        }
        catch (Exception exception){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("FAILED");
        }
        return apiResponseModel;
    }

    public ApiResponseModel getOverpaidInvoices(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<ResOverpaidInvoices> overpaidInvoices=invoiceRepository.findAll().stream().map(invoice -> getOverInvo(invoice)).collect(Collectors.toList());
            overpaidInvoices.removeAll(Collections.singletonList(null));
            apiResponseModel.setCode(200);
            apiResponseModel.setMessage("Success");
            apiResponseModel.setObject(overpaidInvoices);
        }
        catch (Exception exception){
            apiResponseModel.setMessage("Error");
            apiResponseModel.setCode(500);
        }
        return apiResponseModel;
    }

    public ResOverpaidInvoices getOverInvo(Invoice invoice){
        List<Payment> paymentList=paymentRepository.findAllByInv(invoice);
        if (paymentList.size()==0){
            return null;
        }
        Double amount1 = (double) 0;
        for (Payment pay:paymentList) {
            amount1=amount1+pay.getAmount();
        }
        if (amount1>invoice.getAmount()){
            ResOverpaidInvoices resOverpaidInvoices=new ResOverpaidInvoices();
            resOverpaidInvoices.setInvoices_Id(invoice.getId());
            resOverpaidInvoices.setOverpaid_Amount(amount1-invoice.getAmount());

            return resOverpaidInvoices;
        }
        else return null;

    }

    public Invoice getEXIn(Invoice invoice){
        if (invoice.getIssued().after(invoice.getDue())){
            return invoice;
        }
        else {
            return null;
        }
    }

    public ResWrongDateInvoices getWrDaIn(Invoice invoice){
        if (invoice.getOrd().getDate().after(invoice.getIssued())){
            ResWrongDateInvoices resWrongDateInvoices=new ResWrongDateInvoices();
            resWrongDateInvoices.setInvoices_Id(invoice.getId());
            resWrongDateInvoices.setIssued(invoice.getIssued());
            resWrongDateInvoices.setOrder_Id(invoice.getOrd().getId());
            resWrongDateInvoices.setDate(invoice.getOrd().getDate());
            return resWrongDateInvoices;
        }
        else {
            return null;
        }
    }

}

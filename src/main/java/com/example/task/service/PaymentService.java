package com.example.task.service;

import com.example.task.entity.Invoice;
import com.example.task.entity.Payment;
import com.example.task.payloat.ApiResponseModel;
import com.example.task.payloat.ResPayment;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PaymentRepository paymentRepository;


    public ResPayment addPayment(Integer invoce_id){
        ResPayment resPayment=new ResPayment();
        try {
            Optional<Invoice> invoiceOptional=invoiceRepository.findById(invoce_id);
            if (invoiceOptional.isPresent()){
                Payment payment=new Payment();
                payment.setInv(invoiceOptional.get());
                payment.setAmount(invoiceOptional.get().getAmount());
                paymentRepository.save(payment);
                List<Payment> paymentList=paymentRepository.findAllByInv(invoiceOptional.get());
                resPayment.setPayment_status("SUCCESS");
                resPayment.setPayment_details(paymentList);

            }
            else {
                resPayment.setPayment_status("FAILED");
            }

        }
        catch (Exception exception){
            resPayment.setPayment_status("FAILED");
        }
        return resPayment;
    }
    public ApiResponseModel getPayment(Integer payment_id){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            Optional<Payment> paymentOptional=paymentRepository.findById(payment_id);
            if (paymentOptional.isPresent()){
                apiResponseModel.setCode(200);
                apiResponseModel.setMessage("SUCCESS");
                apiResponseModel.setObject(paymentOptional.get());
            }
            else {
                apiResponseModel.setMessage("Payment did not found");
                apiResponseModel.setCode(207);
            }



        }
        catch (Exception exception){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("Error");
        }
        return apiResponseModel;
    }
}

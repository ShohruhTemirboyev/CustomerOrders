package com.example.task.controller;

import com.example.task.smsSender.Service;
import com.example.task.smsSender.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendSms")
public class TwilioController {

    private final Service service;
    @Autowired
    public TwilioController(Service service) {
        this.service = service;
    }

   @PostMapping
    public void sendSms(@RequestBody SmsRequest smsRequest){
          service.sendSms(smsRequest);
    }
}

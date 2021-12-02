package com.example.task.smsSender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data

public class SmsRequest {
    private final String phoneNumber;
    private final String message;

    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}



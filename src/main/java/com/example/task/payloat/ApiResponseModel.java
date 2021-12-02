package com.example.task.payloat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ApiResponseModel extends ApiResponse{

    private Object object;

    public ApiResponseModel(Object object) {
        this.object = object;
    }

    public ApiResponseModel(String message, Integer code, Object object) {
        super(message,code);
        this.object = object;
    }
}

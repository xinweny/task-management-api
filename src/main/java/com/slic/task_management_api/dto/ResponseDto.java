package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private String message;
    private T data;

    public ResponseDto(T data, String message) {
        this.data = data;
        this.message = message;
    }
}

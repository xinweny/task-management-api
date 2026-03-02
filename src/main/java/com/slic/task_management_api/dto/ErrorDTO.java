package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class ErrorDTO<T> {
    private T error;

    public ErrorDTO(T error) {
        this.error = error;
    }
}

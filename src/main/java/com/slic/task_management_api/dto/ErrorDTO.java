package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }
}

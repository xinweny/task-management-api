package com.slic.task_management_api.model.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
}

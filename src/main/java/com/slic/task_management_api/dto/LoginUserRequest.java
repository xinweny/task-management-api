package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}

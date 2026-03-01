package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String email;
    private String password;
}

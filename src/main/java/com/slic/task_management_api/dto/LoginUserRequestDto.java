package com.slic.task_management_api.dto;

import lombok.Getter;

@Getter
public class LoginUserRequestDTO {
    private String email;
    private String password;
}

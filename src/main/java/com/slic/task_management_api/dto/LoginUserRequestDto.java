package com.slic.task_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUserRequestDto {
    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;
}

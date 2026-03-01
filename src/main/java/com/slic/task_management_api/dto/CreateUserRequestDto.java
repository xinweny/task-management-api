package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String name;
    private String email;
    private String password;
}

package com.slic.task_management_api.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDTO {
    private String name;
    private String email;
    private String password;
}

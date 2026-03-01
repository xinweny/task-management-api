package com.slic.task_management_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponse {
    private String token;
    private UserDto user;
    private long expiresIn;
}

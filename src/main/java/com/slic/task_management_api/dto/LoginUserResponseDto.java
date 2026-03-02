package com.slic.task_management_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponseDTO {
    private String token;
    private UserDTO user;
    private long expiresIn;
}

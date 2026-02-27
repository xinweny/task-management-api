package com.slic.task_management_api.dto;

public class AuthDto {
    public record LoginRequest(
        String username,
        String password
    ) {}

    public record LoginResponse(
        String token
    ) {}
}

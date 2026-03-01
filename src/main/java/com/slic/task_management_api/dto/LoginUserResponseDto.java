package com.slic.task_management_api.dto;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    @Data
    public class User {
        private Long id;
        private String email;
        private String name;

        private User(Long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }
    }

    private String token;
    private User user;
    private long expiresIn;

    public LoginUserResponseDto(
        String token,
        Long id,
        String email,
        String name,
        long expiresIn) {
        this.token = token;
        this.user = new User(id, email, name);
        this.expiresIn = expiresIn;
    }
}

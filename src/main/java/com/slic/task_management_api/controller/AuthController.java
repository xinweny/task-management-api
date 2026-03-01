package com.slic.task_management_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.CreateUserRequest;
import com.slic.task_management_api.dto.LoginUserRequest;
import com.slic.task_management_api.dto.LoginUserResponse;
import com.slic.task_management_api.dto.UserDto;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.AuthService;
import com.slic.task_management_api.service.JwtService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody CreateUserRequest createUserRequest) {
        User user = authService.createUser(createUserRequest);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> authenticate(@RequestBody LoginUserRequest loginUserRequest) {
        User authenticatedUser = authService.authenticate(loginUserRequest);

        UserDto user = UserDto
            .builder()
            .id(authenticatedUser.getId())
            .userName(authenticatedUser.getUsername())
            .build();

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginUserResponse response = LoginUserResponse.builder()
            .token(jwtToken)
            .user(user)
            .expiresIn(jwtService.getExpirationTime())
            .build();

        return ResponseEntity.ok(response);
    }
}
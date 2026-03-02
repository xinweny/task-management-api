package com.slic.task_management_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.CreateUserRequestDTO;
import com.slic.task_management_api.dto.LoginUserRequestDTO;
import com.slic.task_management_api.dto.LoginUserResponseDTO;
import com.slic.task_management_api.dto.ResponseDTO;
import com.slic.task_management_api.mapper.UserMapper;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.AuthService;
import com.slic.task_management_api.service.JwtService;
import com.slic.task_management_api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;
    
    private final UserService userService;

    private final UserMapper userMapper;

    public AuthController(
        JwtService jwtService,
        AuthService authService,
        UserService userService,
        UserMapper userMapper
    ) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<?>> register(@RequestBody @Valid CreateUserRequestDTO req) {
        userService.createUser(req);

        return ResponseEntity.ok(ResponseDTO.builder()
            .message("User created successfully")
            .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginUserResponseDTO>> authenticate(@RequestBody @Valid LoginUserRequestDTO loginUserRequest) {
        User user = authService.login(loginUserRequest);

        String jwtToken = jwtService.generateToken(user);

        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
            .token(jwtToken)
            .user(userMapper.toDTO(user))
            .expiresIn(jwtService.getExpirationTime())
            .build();

        return ResponseEntity.ok(ResponseDTO.<LoginUserResponseDTO>builder()
            .data(dto)
            .message("Login successful")
            .build()
        );
    }
}
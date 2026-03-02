package com.slic.task_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.CreateUserRequestDto;
import com.slic.task_management_api.dto.LoginUserRequestDto;
import com.slic.task_management_api.dto.LoginUserResponseDto;
import com.slic.task_management_api.dto.ResponseDto;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.AuthService;
import com.slic.task_management_api.service.JwtService;
import com.slic.task_management_api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthService authService;

    @Autowired
    private final UserService userService;

    public AuthController(
        JwtService jwtService,
        AuthService authService,
        UserService userService
    ) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody CreateUserRequestDto req) {
        User user = userService.createUser(req);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginUserResponseDto>> authenticate(@RequestBody LoginUserRequestDto loginUserRequest) {
        User user = authService.login(loginUserRequest);

        String jwtToken = jwtService.generateToken(user);

        LoginUserResponseDto data = LoginUserResponseDto.builder()
            .token(jwtToken)
            .expiresIn(jwtService.getExpirationTime())
            .build();

        data.setUser(data.new User(
            user.getId(),
            user.getEmail(),
            user.getName()
        ));

        return ResponseEntity.ok(ResponseDto.<LoginUserResponseDto>builder()
            .data(data)
            .message("Login successful")
            .build()
        );
    }
}
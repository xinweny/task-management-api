package com.slic.task_management_api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.LoginUserRequest;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User authenticate(LoginUserRequest params) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                params.getUsername(),
                params.getPassword()
            )
        );

        return userRepository
            .findByUsername(params.getUsername())
            .orElseThrow();
    }
}

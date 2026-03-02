package com.slic.task_management_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.LoginUserRequestDto;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User login(LoginUserRequestDto req) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.email,
                req.password
            )
        );

        return userRepository.findByEmail(req.email);
    }
}

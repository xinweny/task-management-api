package com.slic.task_management_api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.CreateUserRequest;
import com.slic.task_management_api.dto.LoginUserRequest;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest params) {
        User user = User.builder()
            .username(params.getUsername())
            .password(passwordEncoder.encode(params.getPassword()))
            .build();
        return userRepository.save(user);
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

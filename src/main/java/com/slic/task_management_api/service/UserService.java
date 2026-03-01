package com.slic.task_management_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.CreateUserRequest;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
}

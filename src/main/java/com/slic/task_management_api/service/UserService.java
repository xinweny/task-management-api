package com.slic.task_management_api.service;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slic.task_management_api.dto.CreateUserRequestDTO;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.RoleRepository;
import com.slic.task_management_api.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder,
        RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User createUser(CreateUserRequestDTO dto) {
        @Valid User user = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .roles(Arrays.asList(roleRepository.findByName("ROLE_USER"))) // Add user role by default
            .build();
        
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

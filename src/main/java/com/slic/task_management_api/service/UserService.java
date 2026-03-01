package com.slic.task_management_api.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.CreateUserRequestDto;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.RoleRepository;
import com.slic.task_management_api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
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

    public User createUser(CreateUserRequestDto req) {
        User user = User.builder()
            .name(req.getName())
            .password(passwordEncoder.encode(req.getPassword()))
            .roles(Arrays.asList(roleRepository.findByName("ROLE_USER"))) // Add user role by default
            .build();
        
        return userRepository.save(user);
    }
}

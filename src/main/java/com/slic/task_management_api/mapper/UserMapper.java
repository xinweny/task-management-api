package com.slic.task_management_api.mapper;

import org.springframework.stereotype.Component;

import com.slic.task_management_api.dto.UserDTO;
import com.slic.task_management_api.model.User;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return User.builder()
            .name(userDTO.getName())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
            .build();
    }
}

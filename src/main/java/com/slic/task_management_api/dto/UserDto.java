package com.slic.task_management_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // Enforce builder design pattern to enforce immutability and "named parameters"
public class UserDto {
    private Long id;
    private String userName;
}

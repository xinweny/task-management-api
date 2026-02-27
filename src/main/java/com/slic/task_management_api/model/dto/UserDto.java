package com.slic.task_management_api.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String userName;
}

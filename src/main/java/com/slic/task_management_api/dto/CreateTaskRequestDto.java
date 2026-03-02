package com.slic.task_management_api.dto;

import lombok.Getter;

@Getter
public class CreateTaskRequestDTO {
    private String title;
    private Long userId;
}

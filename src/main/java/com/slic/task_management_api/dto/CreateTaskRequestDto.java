package com.slic.task_management_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskRequestDto {
    @NotBlank
    public String title;
}

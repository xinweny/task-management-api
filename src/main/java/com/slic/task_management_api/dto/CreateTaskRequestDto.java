package com.slic.task_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateTaskRequestDto {
    @NotBlank
    private String title;
}

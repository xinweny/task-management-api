package com.slic.task_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateTaskRequestDTO {
    @NotBlank
    private String title;
}

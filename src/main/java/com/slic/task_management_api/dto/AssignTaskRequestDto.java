package com.slic.task_management_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AssignTaskRequestDTO {
    @NotNull
    private Long userId;
}

package com.slic.task_management_api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTaskResponseDto {
    @Data
    @AllArgsConstructor
    public class User {
        private Long id;
        private String name;
    }

    private Long id;
    private String title;
    private User user;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
}

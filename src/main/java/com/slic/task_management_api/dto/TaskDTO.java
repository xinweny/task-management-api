package com.slic.task_management_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDTO {
  private Long id;
  private String title;
  private Boolean completed;
  private UserDTO user;
  private String createdAt;
  private String updatedAt;
}

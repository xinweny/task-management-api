package com.slic.task_management_api.mapper;

import org.springframework.stereotype.Component;

import com.slic.task_management_api.dto.TaskDTO;
import com.slic.task_management_api.model.Task;

@Component
public class TaskMapper implements Mapper<Task, TaskDTO> {
    private UserMapper userMapper = new UserMapper();

    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public TaskDTO toDTO(Task task) {        
        return TaskDTO.builder()
            .id(task.getId())
            .title(task.getTitle())
            .completed(task.getCompleted())
            .user(userMapper.toDTO(task.getUser()))
            .createdAt(task.getCreatedAt().toString())
            .updatedAt(task.getUpdatedAt().toString())
            .build();
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        return Task.builder()
            .title(taskDTO.getTitle())
            .completed(taskDTO.getCompleted())
            .build();
    }
}

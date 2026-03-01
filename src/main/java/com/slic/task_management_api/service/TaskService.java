package com.slic.task_management_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.CreateTaskRequestDto;
import com.slic.task_management_api.model.Task;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(CreateTaskRequestDto params, User user) {
        Task task = Task.builder()
            .title(params.getTitle())
            .user(user)
            .build();
        
        return taskRepository.save(task);
    }
}

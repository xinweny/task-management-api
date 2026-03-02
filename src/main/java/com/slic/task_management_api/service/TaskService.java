package com.slic.task_management_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.slic.task_management_api.dto.CreateTaskRequestDTO;
import com.slic.task_management_api.model.Task;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.repository.TaskRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createTask(CreateTaskRequestDTO params, User user) {
        @Valid Task task = Task.builder()
            .title(params.getTitle())
            .user(user) // Can be null if not assigned
            .build();
        
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Transactional
    public Task assignTaskToUser(Task task, User user) {
        task.setUser(user);

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskStatus(@Valid Task task, Boolean completed) {
        task.setCompleted(completed);

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

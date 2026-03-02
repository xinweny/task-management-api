package com.slic.task_management_api.service;

import java.util.List;

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
            .title(params.title)
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

    public Task assignTaskToUser(Task task, User user) {
        task.setUser(user);

        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Task task, Boolean completed) {
        task.setCompleted(completed);

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    
}

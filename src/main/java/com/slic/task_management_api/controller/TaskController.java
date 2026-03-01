package com.slic.task_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.CreateTaskRequestDto;
import com.slic.task_management_api.dto.ResponseDto;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@PreAuthorize("hasRole('ADMIN')")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<?>> createTask(
      @AuthenticationPrincipal User user,
      @RequestBody CreateTaskRequestDto req
    ) {
        taskService.createTask(req, user);

        return ResponseEntity.ok(new ResponseDto<>(null, "Task created successfully"));
    }
}

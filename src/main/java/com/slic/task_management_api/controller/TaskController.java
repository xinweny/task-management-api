package com.slic.task_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.AssignTaskRequestDto;
import com.slic.task_management_api.dto.CreateTaskRequestDto;
import com.slic.task_management_api.dto.GetTaskResponseDto;
import com.slic.task_management_api.dto.GetTasksResponseDto;
import com.slic.task_management_api.dto.ResponseDto;
import com.slic.task_management_api.model.Task;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.TaskService;
import com.slic.task_management_api.service.UserService;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @Autowired
    private final UserService userService;

    public TaskController(
        TaskService taskService,
        UserService userService
    ) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<?>> createTask(
        @AuthenticationPrincipal User user,
        @RequestBody CreateTaskRequestDto req
    ) {
        taskService.createTask(req, user);

        return ResponseEntity.ok(ResponseDto.builder()
            .message("Task created successfully")
            .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDto<GetTasksResponseDto>> getTasks(
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(ResponseDto.<GetTasksResponseDto>builder()
            .data(null)
            .build()
        );
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseDto<GetTaskResponseDto>> getTask(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);

        GetTaskResponseDto dto = GetTaskResponseDto.builder()
            .id(task.getId())
            .title(task.getTitle())
            .completed(task.getCompleted())
            .createdAt(task.getCreatedAt())
            .updatedAt(task.getUpdatedAt())
            .build();

        User user = task.getUser();

        if (user != null) {
            dto.setUser(dto.new User(user.getId(), user.getName()));
        }

        return ResponseEntity.ok(ResponseDto.<GetTaskResponseDto>builder()
            .data(dto)
            .build()
        );
    }

    @PatchMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<?>> assignTask(  
        @PathVariable Long taskId,
        @RequestBody AssignTaskRequestDto req
    ) {
        User user = userService.getUserById(req.getUserId());

        taskService.assignTaskToUser(taskId, user);

        return ResponseEntity.ok(ResponseDto.builder()
            .message("Task assigned successfully")
            .build()
        );
    }
    
    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<?>> deleteTask(
        @PathVariable Long taskId
    ) {
        taskService.deleteTask(taskId);

        return ResponseEntity.ok(ResponseDto.builder()
            .message("Task deleted successfully")
            .build()
        );
    }
}

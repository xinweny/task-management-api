package com.slic.task_management_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slic.task_management_api.dto.AssignTaskRequestDTO;
import com.slic.task_management_api.dto.CreateTaskRequestDTO;
import com.slic.task_management_api.dto.ResponseDTO;
import com.slic.task_management_api.dto.TaskDTO;
import com.slic.task_management_api.dto.UpdateTaskRequestDTO;
import com.slic.task_management_api.exception.ResourceNotFoundException;
import com.slic.task_management_api.mapper.TaskMapper;
import com.slic.task_management_api.mapper.UserMapper;
import com.slic.task_management_api.model.Task;
import com.slic.task_management_api.model.User;
import com.slic.task_management_api.service.TaskService;
import com.slic.task_management_api.service.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    private final UserService userService;

    private final TaskMapper taskMapper;

    public TaskController(
        TaskService taskService,
        UserService userService,
        TaskMapper taskMapper,
        UserMapper userMapper
    ) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO<?>> createTask(
        @RequestBody CreateTaskRequestDTO req
    ) {
        User user = req.getUserId() != null
            ? userService.getUserById(req.getUserId())
            : null;

        taskService.createTask(req, user);

        return ResponseEntity.ok(ResponseDTO.builder()
            .message("Task created successfully")
            .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDTO<List<TaskDTO>>> getTasks(
        @AuthenticationPrincipal User user
    ) {
        Boolean isAdmin = user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        List<Task> tasks = isAdmin
            ? taskService.getAllTasks() // Get all tasks if admin
            : taskService.getTasksByUserId(user.getId()); // Only get tasks assigned to the user
        
        taskService.getAllTasks();

        return ResponseEntity.ok(ResponseDTO.<List<TaskDTO>>builder()
            .data(tasks.stream().map(taskMapper::toDTO).toList())
            .build()
        );
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseDTO<TaskDTO>> getTask(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);

        return ResponseEntity.ok(ResponseDTO.<TaskDTO>builder()
            .data(taskMapper.toDTO(task))
            .build()
        );
    }

    @PatchMapping(value = "/{taskId}", params = "assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO<?>> assignTask(  
        @PathVariable Long taskId,
        @RequestBody AssignTaskRequestDTO req
    ) {
        User user = userService.getUserById(req.getUserId());

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Task task = taskService.getTaskById(taskId);
        taskService.assignTaskToUser(task, user);
        
        return ResponseEntity.ok(ResponseDTO.builder()
            .message("Task assigned successfully")
            .build()
        );
    }

    @PatchMapping("/{taskId}")
    @PostAuthorize("@taskService.getTaskById(#taskId)?.user?.id == authentication.principal.id")
    public ResponseEntity<ResponseDTO<?>> updateTask(  
        @PathVariable Long taskId,
        @RequestBody UpdateTaskRequestDTO req
    ) {
        Task task = taskService.getTaskById(taskId);

        taskService.updateTaskStatus(task, req.getCompleted());
        
        return ResponseEntity.ok(ResponseDTO.builder()
            .message("Task updated successfully")
            .build()
        );
    }
    
    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO<?>> deleteTask(
        @PathVariable Long taskId
    ) {
        taskService.deleteTask(taskId);

        return ResponseEntity.ok(ResponseDTO.builder()
            .message("Task deleted successfully")
            .build()
        );
    }
}

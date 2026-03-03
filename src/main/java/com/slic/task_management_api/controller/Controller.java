package com.slic.task_management_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    @GetMapping("/")
    public ResponseEntity<String> getHomepage() {
        return ResponseEntity.ok("Task Management API");
    }
}

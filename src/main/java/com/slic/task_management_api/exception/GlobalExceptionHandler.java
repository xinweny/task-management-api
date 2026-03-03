package com.slic.task_management_api.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.slic.task_management_api.dto.ErrorDTO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO<String>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>(e.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO<String>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("User not logged in"),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO<String>> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("Unauthorised"),
            HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDTO<String>> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("Login session expired"),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorDTO<String>> handleJwtException(JwtException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("Invalid JWT token"),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO<String>> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("Incorrect credentials"),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError: fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
            new ErrorDTO<>(errors),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO<String>> handleNoResourceFoundException(NoResourceFoundException e) {
        return new ResponseEntity<>(
            new ErrorDTO<>("Page not found"),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO<String>> handleException(Exception e) {
        e.printStackTrace();

        return new ResponseEntity<>(
            new ErrorDTO<>("Internal Server Error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

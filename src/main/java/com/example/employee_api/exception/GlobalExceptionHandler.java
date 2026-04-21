package com.example.employee_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handle(EmployeeNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
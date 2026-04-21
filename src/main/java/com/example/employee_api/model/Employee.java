package com.example.employee_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private double salary;
}
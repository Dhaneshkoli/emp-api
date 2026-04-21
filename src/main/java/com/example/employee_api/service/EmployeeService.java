package com.example.employee_api.service;


import java.util.List;

import com.example.employee_api.model.Employee;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee create(Employee emp);
    Employee update(Long id, Employee emp);
    void delete(Long id);
}

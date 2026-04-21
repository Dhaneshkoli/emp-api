package com.example.employee_api.service;

import com.example.employee_api.exception.EmployeeNotFoundException;
import com.example.employee_api.model.Employee;
import com.example.employee_api.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public Employee create(Employee emp) {
        return repo.save(emp);
    }

    public Employee update(Long id, Employee emp) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        emp.setId(id);
        return repo.save(emp);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        repo.deleteById(id);
    }
}
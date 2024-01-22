package com.wulp.assignment.controller;

import com.wulp.assignment.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/{personId}")
    public EmployeeDto fetchEmployeeByPersonId(@PathVariable Integer personId) {
        return null;
    }

    @GetMapping("/active")
    public List<EmployeeDto> fetchAllActiveEmployees() {
        return List.of();
    }

    @GetMapping("/active/by-department")
    public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
        return Map.of();
    }

}

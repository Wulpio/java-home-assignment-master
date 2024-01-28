package com.wulp.assignment.controller;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<EmployeeDto> fetchEmployeeByPersonId(@PathVariable Integer personId, @RequestParam Integer departmentId )  {
        EmployeeDto employeeDTObyId = employeeService.getEmployeeDTObyId(personId,departmentId);
        if (employeeDTObyId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employeeDTObyId, HttpStatus.OK);
        }
    }


    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDto>> fetchAllActiveEmployees() {
        List<EmployeeDto> activeEmployees = employeeService.getActiveEmployeeByEndDateIsNull();
        if (activeEmployees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activeEmployees, HttpStatus.OK);
        }

    }

    @GetMapping("/active/by-department")
    public ResponseEntity<Map<String, List<EmployeeDto>>> fetchActiveEmployeesByDepartment() {
        Map<String, List<EmployeeDto>> activeEmployees = employeeService.getActiveEmployeesByDepartment();
        if (activeEmployees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activeEmployees, HttpStatus.OK);
        }
    }

}

package com.wulp.assignment.service;


import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.model.Employee;
import com.wulp.assignment.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class EmployeeService {

    final private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto getEmployeeDTObyId(Integer id) throws SQLException {

        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            return null;
        } else {
            return convertToDTO(employee);
        }
    }

    private EmployeeDto convertToDTO(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setDepartmentName(employee.getDepartmentName());
        employeeDto.setStartDate(employee.getStartDate());
        employeeDto.setEndDate(employee.getEndDate());
        return employeeDto;
    }

}

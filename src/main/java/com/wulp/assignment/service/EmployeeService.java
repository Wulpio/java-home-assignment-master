package com.wulp.assignment.service;


import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.model.Employee;
import com.wulp.assignment.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static EmployeeDto convertToDTO(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setDepartmentName(employee.getDepartmentName());
        employeeDto.setStartDate(employee.getStartDate());
        employeeDto.setEndDate(employee.getEndDate());
        return employeeDto;
    }

    public List<EmployeeDto> getActiveEmployeeByEndDateIsNull() {
        List<Employee> activeEmployees = employeeRepository.findActiveEmployees();

        return activeEmployees.stream().map(EmployeeService::convertToDTO).collect(Collectors.toList());
    }

    public Map<String, List<EmployeeDto>> getActiveEmployeesByDepartment() {
        List<EmployeeDto> activeEmployees = getActiveEmployeeByEndDateIsNull();
        Map<String, List<EmployeeDto>> employeesByDepartment = new HashMap<>();

        for (EmployeeDto employee : activeEmployees) {
            String departmentName = employee.getDepartmentName();

            if (employeesByDepartment.containsKey(departmentName)) {
                employeesByDepartment.get(departmentName).add(employee);

            } else {
                List<EmployeeDto> departmentEmployees = new ArrayList<>();
                departmentEmployees.add(employee);
                employeesByDepartment.put(departmentName, departmentEmployees);
            }
        }
        return employeesByDepartment;
    }

}

package com.wulp.assignment.service;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.model.Employee;
import com.wulp.assignment.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


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

//    public EmployeeDto getEmployeeDTObyId(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("The Employee with ID " + id + " Not Found."));
//        return convertToDTO(employee);
//    }


    public EmployeeDto getEmployeeDTObyId(Integer personId) {
        Employee employee = findById(personId);
        if (employee == null) {
            throw new EntityNotFoundException("Employee with ID (" + personId + ") not found.");
        }
        return convertToDTO(employee);
    }

    public Employee findById(Integer personId) {
        return employeeRepository.findByEmployeeIdPersonId(personId);
    }

    public static EmployeeDto convertToDTO(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getEmployeeId().getPerson().getName());
        employeeDto.setAge(employee.getEmployeeId().getPerson().getAge());
        employeeDto.setDepartmentName(employee.getEmployeeId().getDepartment().getName());
        employeeDto.setStartDate(employee.getStartDate());
        employeeDto.setEmploymentTypeName(employee.getEmploymentType().getName());
        employeeDto.setEndDate(employee.getEndDate());
        return employeeDto;
    }

    public List<EmployeeDto> getActiveEmployeeByEndDateIsNull() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> activeEmployees = employees.stream()
                .filter(employee -> employee.getEndDate() == null)
                .map(EmployeeService::convertToDTO)
                .collect(Collectors.toList());

        return activeEmployees;
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

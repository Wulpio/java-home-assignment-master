package com.wulp.assignment.service;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.model.Department;
import com.wulp.assignment.model.Employee;
import com.wulp.assignment.model.EmploymentType;
import com.wulp.assignment.model.Person;
import com.wulp.assignment.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    public static final LocalDateTime START_DATE = LocalDateTime.of(2015, 05, 5, 12, 00);
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void GIVEN_Employee_WHEN_getEmployeeDTObyId_THEN_return_savedUserInfo_with_detail_checked() {
        //GIVEN
        Person person = new Person(1, "Jenny", 32);
        Department depart = new Department(1, "IT");
        EmploymentType empType = new EmploymentType(1, "FullShifts");

        EmployeeId empId = new EmployeeId(person, depart);

        Employee employee = new Employee(empId, LocalDateTime.now(), null, empType);
        when(employeeRepository.findByEmployeeIdPersonId(person.getId())).thenReturn(employee);

        //WHEN
        EmployeeDto resultEmployee = employeeService.getEmployeeDTObyId(person.getId());

        //THEN
        assertThat(resultEmployee).isNotNull();
        assertThat(resultEmployee.getName()).isEqualTo(employee.getEmployeeId().getPerson().getName());
        assertThat(resultEmployee.getAge()).isEqualTo(employee.getEmployeeId().getPerson().getAge());
        assertThat(resultEmployee.getDepartmentName()).isEqualTo(depart.getName());
        assertThat(resultEmployee.getEmploymentTypeName()).isEqualTo(employee.getEmploymentType().getName());
    }

    @Test
    public void GIVEN_Employee_WHEN_getEmployeeDTObyId_not_found_THEN_return_null() {
        // GIVEN
        int nonExistId = 100;
        when(employeeRepository.findByEmployeeIdPersonId(nonExistId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(EntityNotFoundException.class, () -> {
            employeeService.getEmployeeDTObyId(nonExistId);
        });
    }

    @Test
    public void GIVEN_Employee_List_WHEN_getActiveEmployeeByEndDateIsNull_THEN_return_List_employees() {
        //GIVEN
        Person person1 = new Person(1, "Jenny", 32);
        Person person2 = new Person(2, "Rebeca", 35);
        Department depart = new Department(1, "IT");
        EmploymentType empType = new EmploymentType(1, "FullShifts");

        EmployeeId empId1 = new EmployeeId(person1, depart);
        EmployeeId empId2 = new EmployeeId(person2, depart);

        Employee employee1 = new Employee(empId1, LocalDateTime.now(), null, empType);
        Employee employee2 = new Employee(empId2, START_DATE, LocalDateTime.now(), empType);

        List<Employee> empList = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(empList);

        //WHEN
        List<EmployeeDto> resultEmployees = employeeService.getActiveEmployeeByEndDateIsNull();

        //THEN
        assertThat(resultEmployees).isNotNull();
        assertThat(resultEmployees).hasSize(1);

        EmployeeDto resultEmployeeDto = resultEmployees.get(0);
        assertThat(resultEmployeeDto.getName()).isEqualTo("Jenny");
        assertThat(resultEmployeeDto.getAge()).isEqualTo(32);
        assertThat(resultEmployeeDto.getEndDate()).isNull();
    }

    @Test
    public void GIVEN_Employee_List_WHEN_getActiveEmployeesByDepartment_THEN_return_Map_employees_sort_by_department() {
        //GIVEN
        Person person1 = new Person(1, "Jenny", 32);
        Person person2 = new Person(2, "Rebeca", 35);
        Person person3 = new Person(3, "Teresa", 30);
        Department depart = new Department(1, "IT");
        EmploymentType empType = new EmploymentType(1, "FullShifts");

        EmployeeId empId1 = new EmployeeId(person1, depart);
        EmployeeId empId2 = new EmployeeId(person2, depart);
        EmployeeId empId3 = new EmployeeId(person3, depart);

        Employee employee1 = new Employee(empId1, LocalDateTime.now(), null, empType);
        Employee employee2 = new Employee(empId2, START_DATE, LocalDateTime.now(), empType);
        Employee employee3 = new Employee(empId3, START_DATE, null, empType);
        List<Employee> empList = Arrays.asList(employee1, employee2, employee3);
        when(employeeRepository.findAll()).thenReturn(empList);

        // WHEN
        Map<String, List<EmployeeDto>> empactiveMap = employeeService.getActiveEmployeesByDepartment();

        // THEN
        assertThat(empactiveMap).isNotNull();
        assertThat(empactiveMap.keySet()).containsExactly("IT");

        List<EmployeeDto> resultEmployees = empactiveMap.get("IT");
        assertThat(resultEmployees).hasSize(2);

        EmployeeDto resultEmployeeDto1 = resultEmployees.get(0);
        assertThat(resultEmployeeDto1.getName()).isEqualTo("Jenny");
        assertThat(resultEmployeeDto1.getAge()).isEqualTo(32);
        assertThat(resultEmployeeDto1.getEndDate()).isNull();

        EmployeeDto resultEmployeeDto2 = resultEmployees.get(1);
        assertThat(resultEmployeeDto2.getName()).isEqualTo("Teresa");
        assertThat(resultEmployeeDto2.getAge()).isEqualTo(30);
        assertThat(resultEmployeeDto2.getEndDate()).isNull();
    }

}

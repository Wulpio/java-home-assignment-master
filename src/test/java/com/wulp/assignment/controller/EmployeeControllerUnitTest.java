package com.wulp.assignment.controller;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerUnitTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void GIVEN_mocked_Employee_Id_as_null_object_When_fetchEmployeeByPersonId_THEN_NOT_FOUND_is_returned() {
        //GIVEN
        int personId = 1;
        when(employeeService.getEmployeeDTObyId(personId)).thenReturn(null);

        //WHEN
        ResponseEntity<EmployeeDto> returnValue = employeeController.fetchEmployeeByPersonId(personId);

        //THEN
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(returnValue.getBody()).isNull();
    }

    @Test
    void GIVEN_mocked_Employee_Id_When_fetchEmployeeByPersonId_THEN_OK_status_and_non_null_body_is_returned() {
        //GIVEN
        int personId = 1;
        when(employeeService.getEmployeeDTObyId(personId)).thenReturn(new EmployeeDto());

        //WHEN
        ResponseEntity<EmployeeDto> returnValue = employeeController.fetchEmployeeByPersonId(personId);

        //THEN
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnValue.getBody()).isNotNull();
    }

    @Test
    void GIVEN_mocked_Employees_as_null_WHEN_fetchAllActiveEmployees_THEN_NOT_FOUND_is_returned() {
        //GIVEN
        when(employeeService.getActiveEmployeeByEndDateIsNull()).thenReturn(null);

        //WHEN
        ResponseEntity<List<EmployeeDto>> empList = employeeController.fetchAllActiveEmployees();

        //THEN
        assertThat(empList.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(empList.getBody()).isNull();
    }

    @Test
    void GIVEN_mocked_Employees_WHEN_fetchAllActiveEmployees_THEN_OK_STATUS_is_returned() {
        //GIVEN
        EmployeeDto employee1 = new EmployeeDto();
        EmployeeDto employee2 = new EmployeeDto();
        List<EmployeeDto> empListDto = Arrays.asList(employee1, employee2);
        when(employeeService.getActiveEmployeeByEndDateIsNull()).thenReturn(empListDto);

        //WHEN
        ResponseEntity<List<EmployeeDto>> empList = employeeController.fetchAllActiveEmployees();

        //THEN
        assertThat(empList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(empList.getBody()).isNotNull();
    }

    @Test
    void GIVEN_mocked_Map_Employees_as_null_WHEN_fetchActiveEmployeesByDepartment_THEN_NOT_FOUND_is_returned() {
        //GIVEN
        when(employeeService.getActiveEmployeesByDepartment()).thenReturn(null);

        //WHEN
        ResponseEntity<Map<String, List<EmployeeDto>>> empMap = employeeController.fetchActiveEmployeesByDepartment();

        //THEN
        assertThat(empMap.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(empMap.getBody()).isNull();
    }

    @Test
    void GIVEN_mocked_Map_Employees_as_null_WHEN_fetchActiveEmployeesByDepartment_STATUS_OK_is_returned() {
        //GIVEN
        EmployeeDto employee1 = new EmployeeDto();
        EmployeeDto employee2 = new EmployeeDto();
        List<EmployeeDto> empListDto = Arrays.asList(employee1, employee2);

        Map<String, List<EmployeeDto>> empactiveMap = new LinkedHashMap<>();
        empactiveMap.put("IT", empListDto);

        when(employeeService.getActiveEmployeesByDepartment()).thenReturn(empactiveMap);

        //WHEN
        ResponseEntity<Map<String, List<EmployeeDto>>> empMap = employeeController.fetchActiveEmployeesByDepartment();

        //THEN
        assertThat(empMap.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(empMap.getBody()).isNotNull();
    }

}

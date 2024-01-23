package com.wulp.assignment.service;

import com.wulp.assignment.model.Employee;
import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void GIVEN_Employee_WHEN_getEmployeeDTOId_THEN_return_savedEmployee() throws SQLException {
        //GIVEN
        Employee employee =
                new Employee(1, "Joker", 30, "Business", null, null);
        when(employeeRepository.findEmployeeById(1)).thenReturn(employee);

        //WHEN
        EmployeeDto employeeDTo = employeeService.getEmployeeDTObyId(1);

        //THEN
        assertThat(employeeDTo).isNotNull();
        assertThat(employeeDTo.getName()).isEqualTo(employee.getName());
        assertThat(employeeDTo.getAge()).isEqualTo(employee.getAge());
        assertThat(employeeDTo.getStartDate()).isEqualTo(employee.getStartDate());
        assertThat(employeeDTo.getEndDate()).isEqualTo(employee.getEndDate());
    }

    @Test
    public void GIVEN_Employee_WHEN_getEmployeeDTOId_THEN_return_null() throws SQLException {
        //GIVEN
        int employeeId = 100;
        Employee employee =
                new Employee(1, "Joker", 30, "Business", null, null);
        when(employeeRepository.findEmployeeById(employeeId)).thenReturn(null);

        //WHEN
        EmployeeDto employeeDTo = employeeService.getEmployeeDTObyId(employeeId);

        //THEN
        assertThat(employeeDTo).isNull();
    }

    @Test
    public void GIVEN_Employee_WHEN_convertToDTO_THEN_return_converted_EmployeeDto_checked() {
        //GIVEN
        Employee mock = mock(Employee.class);
        when(mock.getAge()).thenReturn(30);
        when(mock.getName()).thenReturn("Jack");
        when(mock.getDepartmentName()).thenReturn("Technics");
        when(mock.getStartDate()).thenReturn(Instant.parse("2018-03-01T08:00:00Z"));
        when(mock.getEndDate()).thenReturn(Instant.parse("2020-03-01T08:00:00Z"));

        // WHEN
        EmployeeDto result = EmployeeService.convertToDTO(mock);

        // THEN
        assertThat(result.getName()).isEqualTo(mock.getName());
        assertThat(result.getAge()).isEqualTo(mock.getAge());
        assertThat(result.getDepartmentName()).isEqualTo(mock.getDepartmentName());
        assertThat(result.getStartDate()).isEqualTo(mock.getStartDate());
        assertThat(result.getEndDate()).isEqualTo(mock.getEndDate());
    }

    @Test
    public void GIVEN_Employees_WHEN_getActiveEmployeeByEndDateIsNull_THEN_return_activeEmployees() throws SQLException {
        //GIVEN
        Employee employee1 =
                new Employee(1, "Joker", 30, "Business", Instant.now(), null);
        Employee employee2 =
                new Employee(2, "Emir", 30, "Stock", Instant.now(), null);
        Employee employee3 =
                new Employee(3, "Goer", 30, "Quality", Instant.now(), null);

        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3);
        when(employeeRepository.findActiveEmployees()).thenReturn(employeeList);

        //WHEN
        List<EmployeeDto> employeeDToList = employeeService.getActiveEmployeeByEndDateIsNull();

        //THEN
        verify(employeeRepository, times(1)).findActiveEmployees();
        assertThat(employeeDToList).hasSize(3);
    }

    @Test
    public void GIVEN_Employees_WHEN_getActiveEmployeeByEndDateIsNull_THEN_return_empty_employeesList() throws SQLException {
        //GIVEN
        Employee employee1 =
                new Employee(1, "Joker", 30, "Business", null, Instant.now());
        Employee employee2 =
                new Employee(2, "Emir", 30, "Stock", null, Instant.now());
        Employee employee3 =
                new Employee(3, "Goer", 30, "Quality", null, Instant.now());

        given(employeeRepository.findActiveEmployees()).willReturn(Collections.EMPTY_LIST);

        //WHEN
        List<EmployeeDto> employeeDToList = employeeService.getActiveEmployeeByEndDateIsNull();

        //THEN
        verify(employeeRepository, times(1)).findActiveEmployees();
        assertThat(employeeDToList).isEmpty();
        assertThat(employeeDToList.size()).isEqualTo(0);
    }

    @Test
    public void GIVEN_Employees_List_WHEN_getActiveEmployeesByDepartment_THEN_return_employees_Map() throws SQLException {
        //GIVEN
        Employee employee1 =
                new Employee(1, "Joker", 30, "Business", Instant.now(), null);
        Employee employee2 =
                new Employee(2, "Emir", 30, "Stock", Instant.now(), null);
        Employee employee3 =
                new Employee(3, "Goer", 30, "Quality", Instant.now(), null);
        Employee employee4 =
                new Employee(4, "Jessy", 30, "Quality", Instant.now(), null);

        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3, employee4);
        given(employeeRepository.findActiveEmployees()).willReturn(employeeList);

        //WHEN
        Map<String, List<EmployeeDto>> employeesByDepartment = employeeService.getActiveEmployeesByDepartment();

        //THEN
        verify(employeeRepository, times(1)).findActiveEmployees();
        assertThat(employeesByDepartment).hasSize(3);
        assertThat(employeesByDepartment).containsKey("Business");
        assertThat(employeesByDepartment).containsKey("Stock");
        assertThat(employeesByDepartment).containsKey("Quality");

        assertThat(employeesByDepartment.get("Business")).hasSize(1);
        assertThat(employeesByDepartment.get("Stock")).hasSize(1);
        assertThat(employeesByDepartment.get("Quality")).hasSize(2);
    }

}

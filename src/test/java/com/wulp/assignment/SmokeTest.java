package com.wulp.assignment;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.repository.EmployeeRepository;
import com.wulp.assignment.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void GIVEN_exists_employees_DB_WHEN_called_all_requests_THEN_all_worked_correctly() {
        //GIVEN checked DB
        int personId = 1;
        assertThat(employeeRepository.findAll()).hasSize(20);
        EmployeeDto existEmp = employeeService.getEmployeeDTObyId(personId);

        //WHEN GetEmployeeById
        ResponseEntity<EmployeeDto> returnedEmployee = restTemplate.getForEntity(
                "http://localhost:" + port + "/employee/{personId}",
                EmployeeDto.class, Objects.requireNonNull(personId));

        //THEN returned EmployeeDto
        assertThat(returnedEmployee).isNotNull();
        assertThat(returnedEmployee.getBody().getName()).isEqualTo(existEmp.getName());

        //WHEN fetchAllActiveEmployees
        EmployeeDto[] activeEmployees = restTemplate.getForObject(
                "http://localhost:" + port + "/employee/active", EmployeeDto[].class);

        //THEN returned employees
        assertThat(activeEmployees).hasSize(17);
        assertThat(activeEmployees[10].getName()).isEqualTo("Manon Blackwell");
        assertThat(activeEmployees[16].getName()).isEqualTo("Verity Bob");

        //WHEN fetchActiveEmployeesByDepartment
        ResponseEntity<Map<String, List<EmployeeDto>>> activeEmpByDepart = restTemplate.exchange(
                "http://localhost:" + port + "/employee/active/by-department",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, List<EmployeeDto>>>() {
                }
        );

        //THEN returned employees sort by departmentName
        assertThat(activeEmpByDepart.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(activeEmpByDepart.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, List<EmployeeDto>> body = activeEmpByDepart.getBody();
        assertThat(body).isNotNull();

        List<EmployeeDto> itDepartment = body.get("Management");
        List<EmployeeDto> hrDepartment = body.get("Technology consulting");

        assertThat(itDepartment).isNotEmpty();
        assertThat(hrDepartment).isNotNull();

    }

}

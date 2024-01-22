package com.wulp.assignment.controller;

import com.wulp.assignment.dto.EmployeeDto;
import com.wulp.assignment.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerRestTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void GIVEN_user_info_dto_WHEN_saved_user_info_to_DB_THEN_saved_user_info_returned_checked() {
        //GIVEN


        //WHEN
        ResponseEntity<EmployeeDto> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/employee/{personId}", EmployeeDto.class, 1);

        //THEN
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Hasnain Frame");

    }

}

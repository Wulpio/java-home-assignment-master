package com.wulp.assignment.repository;


import com.wulp.assignment.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//@Sql(scripts = {"classpath:test/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private static EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        // Perform any setup if needed before each test method
    }

    @Test
    void GIVEN_employee_WHEN_findEmployeeById_THEN_return_employee_Object_find_By_Id() {
        //GIVEN
        assertThat(employeeRepository.findAll()).isNotNull();

        employeeRepository.save(new Employee());

        //WHEN
        employeeRepository.findByEmployeeIdPersonId(1);


//        //THEN
//        assertThat(returnedEmployee).isNotNull();
//        assertThat(returnedEmployee.getName()).isEqualTo("John Doe");
//        assertThat(returnedEmployee.getAge()).isEqualTo(30);
//        assertThat(returnedEmployee.getDepartmentName()).isEqualTo("IT");
//        assertThat(returnedEmployee.getStartDate()).isEqualTo("2021-12-31T23:00:00Z");
//        assertThat(returnedEmployee.getEndDate()).isEqualTo("2021-12-31T23:00:00Z");
    }


    @Test
    void GIVEN_employees_WHEN_findEmployees_THEN_return_List_active_employees_which_endDate_is_null() {

    }
}

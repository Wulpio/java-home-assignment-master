package com.wulp.assignment.repository;


import com.wulp.assignment.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback
@SpringBootTest
public class EmployeeRepositoryTest {

    private static final String URL = "jdbc:h2:mem:assignment";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    @Autowired
    private static EmployeeRepository employeeRepository;

    @BeforeAll
    static void setUp() {
       // employeeRepository = new EmployeeRepository();
    }

    @Test
    void GIVEN_employee_WHEN_findEmployeeById_THEN_return_employee_Object_find_By_Id() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String insertTestData = "INSERT INTO PERSON (ID, NAME, AGE) VALUES (120, 'John Doe', 30)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO DEPARTMENT (ID, NAME) VALUES (120, 'IT')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO EMPLOYMENT_TYPE (ID, NAME) VALUES (120, 'Full Time Employee')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO EMPLOYEE (PERSON_ID, DEPARTMENT_ID, START_DATE, END_DATE,EMPLOYMENT_TYPE_ID) "
                    + "VALUES (120, 120, '2022-01-01 00:00:00', null,120)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //WHEN
        //Employee returnedEmployee = employeeRepository.findById(120);

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

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String insertTestData = "INSERT INTO PERSON (ID, NAME, AGE) VALUES (121, 'Trevor Mars', 30)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            String insertTestData1 = "INSERT INTO PERSON (ID, NAME, AGE) VALUES (122, 'Branislav Mars', 35)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData1)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO DEPARTMENT (ID, NAME) VALUES (121, 'IT')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO EMPLOYMENT_TYPE (ID, NAME) VALUES (121, 'Full Time Employee')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData = "INSERT INTO EMPLOYEE (PERSON_ID, DEPARTMENT_ID, START_DATE, END_DATE,EMPLOYMENT_TYPE_ID) "
                    + "VALUES (121, 121, '2022-01-01 00:00:00', null,121)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData)) {
                preparedStatement.executeUpdate();
            }

            insertTestData1 = "INSERT INTO EMPLOYEE (PERSON_ID, DEPARTMENT_ID, START_DATE, END_DATE,EMPLOYMENT_TYPE_ID) "
                    + "VALUES (122, 122,'2022-01-01 00:00:00', null,122)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertTestData1)) {
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //WHEN
        List<Employee> activeEmployees = employeeRepository.findAll(); //TODO active = endDate = null;

        //THEN

        assertThat(activeEmployees).hasSize(2);
    }
}









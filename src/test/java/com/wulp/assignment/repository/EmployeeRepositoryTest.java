package com.wulp.assignment.repository;

import com.wulp.assignment.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void GIVEN_employee_WHEN_findEmployeeById_THEN_return_employee_Object_find_By_Id() {
        //GIVEN
        assertThat(employeeRepository.findAll()).isEmpty();
//        Employee employee = new Employee();
//        Person  person  = new Person(1,"Jack",32);
//        employeeRepository.save(employee);

        //WHEN
        Employee findEmployee = employeeRepository.findByEmployeeIdPersonId(1);

        assertThat(findEmployee).isNull();

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

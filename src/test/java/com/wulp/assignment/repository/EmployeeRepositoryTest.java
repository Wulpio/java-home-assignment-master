package com.wulp.assignment.repository;

import com.wulp.assignment.model.Department;
import com.wulp.assignment.model.Employee;
import com.wulp.assignment.model.EmploymentType;
import com.wulp.assignment.model.Person;
import com.wulp.assignment.service.EmployeeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmploymentTypeRepository employmentTypeRepository;

    @Test
    void GIVEN_employee_WHEN_findEmployeeById_THEN_return_employee_Object_find_By_Id() {
        //GIVEN
        Person person = new Person(1, "Jehny", 32);
        personRepository.save(person);

        Department depart = new Department(1, "IT");
        departmentRepository.save(depart);

        EmploymentType empType = new EmploymentType(1, "FullShifts");
        employmentTypeRepository.save(empType);

        EmployeeId empId = new EmployeeId(person, depart);

        Employee employee = new Employee(empId, LocalDateTime.now(), null, empType);
        employeeRepository.save(employee);

        // WHEN
        Employee foundEmployee = employeeRepository.findByEmployeeIdPersonId(1);

        // THEN
        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getEmployeeId().getPerson().getId()).isEqualTo(1);
        assertThat(foundEmployee.getEmployeeId().getPerson()).isSameAs(person);
    }

    @Test
    void GIVEN_employees_WHEN_findEmployees_THEN_return_List_active_employees_which_endDate_is_null() {

    }
}

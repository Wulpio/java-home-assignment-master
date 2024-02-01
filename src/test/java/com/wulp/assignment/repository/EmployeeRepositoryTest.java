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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void GIVEN_incorrect_personId_personId_WHEN_findEmployeeByIdPersonId_THEN_return_null() {
        //GIVEN
        int personId = -1000;
        assertThat(employeeRepository.findAll()).isNotEmpty();

        // WHEN
        Employee notFoundEmployee = employeeRepository.findByEmployeeIdPersonId(personId);

        // THEN
        assertThat(notFoundEmployee).isNull();
        assertThat(employeeRepository.findAll()).isNotEmpty();
    }

    @Test
    void GIVEN_employee_WHEN_findEmployeeByIdPersonId_THEN_return_employee_Object_find_By_Id() {
        //GIVEN
        Person person = new Person(1, "Jenny", 32);
        Department depart = new Department(1, "IT");
        EmploymentType empType = new EmploymentType(1, "FullShifts");

        EmployeeId empId = new EmployeeId(person, depart);

        Employee employee = new Employee(empId, LocalDateTime.now(), null, empType);
        employeeRepository.save(employee);

        // WHEN
        Employee foundEmployee = employeeRepository.findByEmployeeIdPersonId(person.getId());

        // THEN
        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getEmployeeId().getPerson().getId()).isEqualTo(1);
        assertThat(foundEmployee.getEmployeeId().getPerson()).isSameAs(person);
    }

    @Test
    void GIVEN_employees_WHEN_findEmployeeByIdPersonId_THEN_return_correct_object() {
        //GIVEN
        assertThat(employeeRepository.findAll()).hasSize(20);

        //WHEN
        Employee foundEmployee = employeeRepository.findByEmployeeIdPersonId(5);

        //THEN
        assertThat(foundEmployee.getEmployeeId().getPerson().getName()).isEqualTo("Kieran Hayden");
    }

    @Test
    void GIVEN_employee_WHEN_save_THEN_return_saved_employee() {
        //GIVEN
        employeeRepository.deleteAll();
        Person person = new Person(1, "Gilian", 32);
        Department depart = new Department(1, "IT");
        EmploymentType empType = new EmploymentType(1, "FullShifts");
        EmployeeId empId = new EmployeeId(person, depart);

        Employee employee = new Employee(empId, LocalDateTime.now(), null, empType);

        //WHEN
        Employee savedEmployee = employeeRepository.save(employee);

        //THEN
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmployeeId().getPerson().getName()).isEqualTo("Gilian");
        assertThat(savedEmployee.getEmployeeId().getPerson().getId()).isGreaterThan(0);
    }

    @Test
    void GIVEN_employees_WHEN_findAll_THEN_return_list_of_employees() {
        //GIVEN
        assertThat(employeeRepository.findAll()).hasSize(20);

        //WHEN
        List<Employee> employeesList = employeeRepository.findAll();

        //THEN
        assertThat(employeesList).isNotNull();
        assertThat(employeesList).hasSize(20);
    }

    @Test
    void GIVEN_employees_WHEN_deleteAll_THEN_return_empty_List() {
        //GIVEN
        assertThat(employeeRepository.findAll()).hasSize(20);

        //WHEN
        employeeRepository.deleteAll();

        //THEN
        assertThat(employeeRepository.findAll()).isEmpty();
    }

}

package com.wulp.assignment.repository;

import com.wulp.assignment.model.Employee;
import com.wulp.assignment.service.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {

    Employee findByEmployeeIdPersonId(Integer personId);

}

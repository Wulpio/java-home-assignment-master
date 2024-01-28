package com.wulp.assignment.repository;

import com.wulp.assignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee,Long>{



    List<Employee> findAll();

}

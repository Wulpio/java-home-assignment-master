package com.wulp.assignment.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
@IdClass(Employee.class)
public class Employee  implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )    Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "START_DATE", nullable = false)
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "EMPLOYMENT_TYPE_ID", nullable = false)
    private EmploymentType employmentType;

    public Employee(Integer personId, Integer departmentId) {

    }
}

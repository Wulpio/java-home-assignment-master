package com.wulp.assignment.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column
    private String name;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column
    private Instant startDate;

    @Column
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "EMPLOYMENT_TYPE_ID", nullable = false)
    private EmploymentType employmentType;

}

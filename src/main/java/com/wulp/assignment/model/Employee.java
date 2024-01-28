package com.wulp.assignment.model;

import com.wulp.assignment.service.EmployeeId;
import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee  {

    @EmbeddedId
    private EmployeeId id;

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

}

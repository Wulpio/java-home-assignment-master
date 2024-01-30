package com.wulp.assignment.model;

import com.wulp.assignment.service.EmployeeId;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @EmbeddedId
    private EmployeeId employeeId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "EMPLOYMENT_TYPE_ID", nullable = false)
    private EmploymentType employmentType;

}

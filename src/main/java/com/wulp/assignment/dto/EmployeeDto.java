package com.wulp.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import com.wulp.assignment.model.Department;
import com.wulp.assignment.model.EmploymentType;
import com.wulp.assignment.model.Person;
import lombok.*;

import java.security.Timestamp;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class EmployeeDto {
    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String departmentName;

    @NotNull
    private String employmentTypeName;

    @NotNull
    private Timestamp startDate;

    private Timestamp endDate;

    @JsonIgnore
    public boolean isActive() {
        return endDate == null;
    }

}

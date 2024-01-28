package com.wulp.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.*;

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
    private Instant startDate;

    private Instant endDate;

    @JsonIgnore
    public boolean isActive() {
        return endDate == null;
    }

}

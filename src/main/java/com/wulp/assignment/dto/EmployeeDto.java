package com.wulp.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

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
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonIgnore
    public boolean isActive() {
        return endDate == null;
    }

}

package com.wulp.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

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

package com.wulp.assignment.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYMENT_TYPE")
public class EmploymentType {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column
    private String name;


}

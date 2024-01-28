package com.wulp.assignment.service;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EmployeeId  implements Serializable {

    private Integer personId;
    private Integer departmentId;

}

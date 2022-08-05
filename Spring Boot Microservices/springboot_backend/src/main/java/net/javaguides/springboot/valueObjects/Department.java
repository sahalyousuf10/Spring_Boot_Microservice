package net.javaguides.springboot.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private long departmentId;
    private String depName;
    private String depAddress;
    private String depCode;
}

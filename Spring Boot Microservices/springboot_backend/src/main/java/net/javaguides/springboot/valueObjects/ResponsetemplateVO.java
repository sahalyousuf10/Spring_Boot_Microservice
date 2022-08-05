package net.javaguides.springboot.valueObjects;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.springboot.model.Employee;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsetemplateVO {

//    private Employee employee;
    private Department department;
    private List<Employee> employeeList;
}

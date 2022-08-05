package com.sahal.DepartmentService.model;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;
    @Column(name = "depname")
    private String depName;
    @Column(name = "depaddress")
    private String depAddress;
    @Column(name = "depcode")
    private String depCode;
}

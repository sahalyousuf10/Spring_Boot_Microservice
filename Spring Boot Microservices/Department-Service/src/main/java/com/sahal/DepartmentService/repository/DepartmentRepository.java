package com.sahal.DepartmentService.repository;

import com.sahal.DepartmentService.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


    @Query(value = "SELECT * FROM department WHERE depname = :depname", nativeQuery = true)
    Department findDepartmentByName(@Param("depname") String depname);

//    @Query(value = "SELECT FROM department WHERE department_id = 12 AND activity_status = 1", nativeQuery = true)
//    Department findDepartmentByName();
}

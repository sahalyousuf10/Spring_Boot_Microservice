package com.sahal.DepartmentService.service;

import com.sahal.DepartmentService.exception.ResourceNotFoundException;
import com.sahal.DepartmentService.model.Department;
import com.sahal.DepartmentService.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    public List<Department> getAllDep() {

        return departmentRepository.findAll();
    }

    public ResponseEntity<Department> getDepbtID(Long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found: "+id));
        return ResponseEntity.ok(department);
    }

    public Department saveDep(Department department) {

        return departmentRepository.save(department);
    }

    public List<Department> saveListDep(List<Department> departments) {

        return departmentRepository.saveAll(departments);
    }

    public ResponseEntity<Department> updateDep(Long id, Department department) {

        Department updateDepartment = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found by id: "+ id));
        updateDepartment.setDepName(department.getDepName());
        updateDepartment.setDepAddress(department.getDepAddress());
        updateDepartment.setDepCode(department.getDepCode());
        departmentRepository.save(updateDepartment);
        return ResponseEntity.ok(updateDepartment);
    }

    public ResponseEntity<HttpStatus> deleteDep(long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Departemnt not found by id: "+id));
        departmentRepository.delete(department);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteAll(){

        departmentRepository.deleteAll();
        return new   ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public Department getDepByName(String name) {

        return departmentRepository.findDepartmentByName(name);
    }
}

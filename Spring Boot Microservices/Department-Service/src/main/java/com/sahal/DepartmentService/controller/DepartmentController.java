package com.sahal.DepartmentService.controller;

import com.sahal.DepartmentService.model.Department;
import com.sahal.DepartmentService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> getAllDep(){

        return service.getAllDep();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepbyId(@PathVariable long id){

        return service.getDepbtID(id);
    }

    @GetMapping("/dep/{name}")
    public ResponseEntity<Department> getDepByName(@PathVariable String name){

        Department department = service.getDepByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(department);
    }

    @PostMapping
    public Department saveDep(@RequestBody Department department){

        return service.saveDep(department);
    }

    @PostMapping("/all")
    public List<Department> saveListDep(@RequestBody List<Department> departments){

        return service.saveListDep(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDep(@PathVariable long id, @RequestBody Department department){

        return service.updateDep(id, department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable long id){

        return service.deleteDep(id);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<HttpStatus> deleteAll(){

        return service.deleteAll();
    }




}

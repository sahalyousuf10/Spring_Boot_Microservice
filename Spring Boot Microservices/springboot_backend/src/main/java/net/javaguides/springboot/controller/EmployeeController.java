package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;
import net.javaguides.springboot.valueObjects.ResponsetemplateVO;
import org.bouncycastle.math.ec.ECMultiplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmp();
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getAllDepartmentEmployees(@PathVariable long id){
        List<Employee> employeeList = employeeService.getAllDepartmentEmp(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> getEmployeesByStatus(@PathVariable String status){
        List<Employee> employeeList = employeeService.getEmpByStatus(status);
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
    }

//    @GetMapping("/active")
//    public ResponseEntity<List<Employee>> getAllActiveEmployees(){
//        List<Employee> employeeList = employeeService.getAllActiveEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/nonactive")
//    public ResponseEntity<List<Employee>> getAllNonActiveEmployees(){
//        List<Employee> employeeList = employeeService.getAllNonActiveEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/backend")
//    public ResponseEntity<List<Employee>> getAllBackendEmployees(){
//        List<Employee> employeeList = employeeService.getAllBackendEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/frontend")
//    public ResponseEntity<List<Employee>> getAllFrontendEmployees(){
//        List<Employee> employeeList = employeeService.getAllFrontendEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/devops")
//    public ResponseEntity<List<Employee>> getAllDevopsEmployees(){
//        List<Employee> employeeList = employeeService.getAllDevopsEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/automation")
//    public ResponseEntity<List<Employee>> getAllAutomationEmployees(){
//        List<Employee> employeeList = employeeService.getAllAutomationEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }
//
//    @GetMapping("/fullstack")
//    public ResponseEntity<List<Employee>> getAllFullstackEmployees(){
//        List<Employee> employeeList = employeeService.getAllFullstackEmp();
//        return ResponseEntity.status(HttpStatus.FOUND).body(employeeList);
//    }

    //build create employee Rest API
    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){

        Employee savedEmployee = employeeService.saveEmp(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Employee>> saveAllEmp(@Valid @RequestBody List<Employee> listEmp){

        List<Employee> employeeList = employeeService.saveListofEmp(listEmp);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeList);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){

        Employee employee = employeeService.getImpbyID(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Employee> updateEmployee(@Valid @PathVariable long id,@RequestBody Employee employeeDetails){

        employeeService.updateImp(id, employeeDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDetails);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id){

        Employee deletedEmployee = employeeService.deleteEmp(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedEmployee);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> DeleteAllEmp(){

        employeeService.deleteAllEmp();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponsetemplateVO getEmpWithDep(@PathVariable("id") Long empId){

        return employeeService.getUserWithDep(empId);
    }

    @GetMapping("/dep/{name}")
    public ResponsetemplateVO getAllEmployeesOfDepartment(@PathVariable("name") String name){

        return employeeService.getAllEmpOfDep(name);
    }

}

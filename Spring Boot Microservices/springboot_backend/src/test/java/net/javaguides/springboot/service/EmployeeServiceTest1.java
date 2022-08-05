package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeServiceTest1 {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    @DisplayName("Add an Employee")
    public void testAddEmployee () throws Exception{

        //create employee
        Employee employee = new Employee();
        employee.setFirstname("Fahad");
        employee.setLastname("Khan");
        employee.setEmailid("fahadkhan@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee newEmployee = employeeService.saveEmp(employee);

        assertEquals("Fahad" , newEmployee.getFirstname());
    }

    @Test
    @DisplayName("Add List of Employee")
    public void testAddAllEmp() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Mahad");
        employee.setLastname("Kamran");
        employee.setEmailid("fmahadkamran@gmail.com");
        employee.setDepartmentId(13L);

        Employee employee2 = new Employee();
        employee2.setFirstname("Moid");
        employee2.setLastname("Mughal");
        employee2.setEmailid("MoidMughal@gmail.com");
        employee2.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);

        when(employeeRepository.saveAll(anyList())).thenReturn(employeeList);
        List<Employee> newEmpList = employeeService.saveListofEmp(employeeList);
        assertEquals("Mahad", newEmpList.get(0).getFirstname());
        assertEquals("Moid", newEmpList.get(1).getFirstname());

    }

    @DisplayName("Get All Employees")
    @Test
    public void getAllRecords_success() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Mahad");
        employee.setLastname("Kamran");
        employee.setEmailid("fmahadkamran@gmail.com");
        employee.setDepartmentId(13L);

        Employee employee2 = new Employee();
        employee2.setFirstname("Moid");
        employee2.setLastname("Mughal");
        employee2.setEmailid("MoidMughal@gmail.com");
        employee2.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> newemployeeList = employeeService.getAllEmp();
        assertEquals("Moid", newemployeeList.get(1).getFirstname());
        assertEquals("Mughal", newemployeeList.get(1).getLastname());
        assertEquals(employeeList.get(0), newemployeeList.get(0));

    }

    @Test
    @DisplayName("Get Employee By Id")
    public void getEmpById() throws Exception{

        Employee employee2 = new Employee();
        employee2.setId(3L);
        employee2.setFirstname("Moid");
        employee2.setLastname("Mughal");
        employee2.setEmailid("MoidMughal@gmail.com");
        employee2.setDepartmentId(14L);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee2));
        Employee newEmp = employeeService.getImpbyID(3L);
        assertEquals("Moid", newEmp.getFirstname());

    }

    @Test
    @DisplayName("Delete an Employee")
    public void deleteEmp() throws Exception{

        Employee employee = new Employee();
        employee.setId(3L);
        employee.setFirstname("Moid");
        employee.setLastname("Mughal");
        employee.setEmailid("MoidMughal@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee));
        Employee employee1 = employeeService.deleteEmp(3L);

        assertEquals("Moid", employee1.getFirstname());
//        assertEquals(null, response.getBody());
//        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Delete All Employees")
    public void deleteAll() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Mahad");
        employee.setLastname("Kamran");
        employee.setEmailid("fmahadkamran@gmail.com");
        employee.setDepartmentId(13L);

        Employee employee2 = new Employee();
        employee2.setFirstname("Moid");
        employee2.setLastname("Mughal");
        employee2.setEmailid("MoidMughal@gmail.com");
        employee2.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);

        int response = employeeService.deleteAllEmp();
//        assertEquals(null, response.getBody());
//        assertEquals(204, response.getStatusCodeValue());

    }
}

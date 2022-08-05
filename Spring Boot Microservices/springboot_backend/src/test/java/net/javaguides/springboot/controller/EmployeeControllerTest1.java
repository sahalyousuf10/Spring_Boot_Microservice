package net.javaguides.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testCreateEmployee() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Haris");
        employee.setLastname("Yousuf");
        employee.setEmailid("haris.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeService.saveEmp(any(Employee.class))).thenReturn(employee);
        ResponseEntity<Employee> emp = employeeController.createEmployee(employee);
        assertNotNull(emp);
    }

    @Test
    public void getAllEmp(){

        Employee employee = new Employee();
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        Employee employee1 = new Employee();
        employee1.setFirstname("saib");
        employee1.setLastname("Yousuf");
        employee1.setEmailid("saib.yousuf10@gmail.com");
        employee1.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeService.getAllEmp()).thenReturn(employeeList);
        ResponseEntity<List<Employee>> emp = employeeController.getAllEmployees();
        assertNotNull(emp);
    }

    @Test
    public void getEmpbyId(){

        Employee employee = new Employee();
        employee.setId(3L);
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeService.getImpbyID(3L)).thenReturn(employee);
        ResponseEntity<Employee> emp = employeeController.getEmployeeById(3L);
        assertEquals("Sahal", emp.getBody().getFirstname());
    }


    @Test
    public void deletebyId(){

        Employee employee = new Employee();
        employee.setId(3L);
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeService.deleteEmp(3L)).thenReturn(employee);
        ResponseEntity<Employee> response = employeeController.deleteEmployee(3L);
        assertEquals(HttpStatus.NO_CONTENT, response.getBody());
    }

    @Test
    public void deleteAll(){

        Employee employee = new Employee();
        employee.setId(3L);
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        Employee employee1 = new Employee();
        employee1.setFirstname("saib");
        employee1.setLastname("Yousuf");
        employee1.setEmailid("saib.yousuf10@gmail.com");
        employee1.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeService.deleteAllEmp()).thenReturn(1);
        ResponseEntity<HttpStatus> response = employeeController.DeleteAllEmp();
        assertEquals(HttpStatus.NO_CONTENT, response.getBody());
    }

    @Test
    public void testCreateAllEmployee() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        Employee employee1 = new Employee();
        employee1.setFirstname("saib");
        employee1.setLastname("Yousuf");
        employee1.setEmailid("saib.yousuf10@gmail.com");
        employee1.setDepartmentId(14L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeService.saveListofEmp(anyList())).thenReturn(employeeList);
        ResponseEntity<List<Employee>> emp = employeeController.saveAllEmp(employeeList);
        assertNotNull(emp);
        assertEquals("Sahal", emp.getBody().get(0).getFirstname());
    }

    @Test
    public void testCreateEmployee1() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        when(employeeService.saveEmp(any(Employee.class))).thenReturn(employee);


        ResultActions response = mockMvc.perform(post("/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));


        response.andDo(print())

                .andExpect(jsonPath("$.firstname", is(employee.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(employee.getLastname())))
                .andExpect(jsonPath("$.emailid", is(employee.getEmailid())))
                .andExpect(jsonPath("$.departmentId", is(employee.getDepartmentId().intValue())))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateAllEmployee1() throws Exception{

        Employee employee = new Employee();
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        Employee employee1 = new Employee();
        employee1.setFirstname("saib");
        employee1.setLastname("Yousuf");
        employee1.setEmailid("saib.yousuf10@gmail.com");
        employee1.setDepartmentId(13L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeService.saveListofEmp(anyList())).thenReturn(employeeList);

        ResultActions response = mockMvc.perform(post("/employees/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeList)));

        response.andDo(print())

                .andExpect(jsonPath("$[0].firstname", is(employeeList.get(0).getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(employeeList.get(0).getLastname())))
                .andExpect(jsonPath("$[0].emailid", is(employeeList.get(0).getEmailid())))
                .andExpect(jsonPath("$[0].departmentId", is(employeeList.get(0).getDepartmentId().intValue())))
                .andExpect(jsonPath("$[1].firstname", is(employeeList.get(1).getFirstname())))
                .andExpect(jsonPath("$[1].lastname", is(employeeList.get(1).getLastname())))
                .andExpect(jsonPath("$[1].emailid", is(employeeList.get(1).getEmailid())))
                .andExpect(jsonPath("$[1].departmentId", is(employeeList.get(1).getDepartmentId().intValue())))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEmp() throws Exception{

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstname("Sahal");
        employee.setLastname("Yousuf");
        employee.setEmailid("sahal.yousuf10@gmail.com");
        employee.setDepartmentId(14L);

        Employee employee1 = new Employee();
        employee1.setFirstname("saib");
        employee1.setLastname("Yousuf");
        employee1.setEmailid("saib.yousuf10@gmail.com");
        employee1.setDepartmentId(13L);

        employeeList.add(employee);
        employeeList.add(employee1);

        when(employeeService.getAllEmp()).thenReturn(employeeList);

        ResultActions response = mockMvc.perform(get("/employees"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(employeeList.size())));

    }





//    @MockBean
//    private EmployeeController employeeController;

//    @Test
//    public void testAddEmployee() throws Exception {
//
//        Employee aEmployee = new Employee();
//        aEmployee.setId(13L);
//        aEmployee.setFirstname("Sahal");
//        aEmployee.setLastname("Yousuf");
//        aEmployee.setEmailid("sahal.yousuf10@gmail.com");
//        aEmployee.setDepartmentId(14L);
//
//        when(employeeService.getImpbyID(aEmployee.getId()))
//                .thenReturn(ResponseEntity.ok(aEmployee));
//        mockMvc.perform(get("/employees/api/13"))
//                .andDo(print())
//                .andExpect(status().isOk());
//        ResponseEntity<Employee> newEmp = employeeController.getEmployeeById(13L);
//
//
//    }
}

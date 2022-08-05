package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Optional<Object> findAllBydepartmentId(Long id);
    // all crud database methods
//
//    @Query(value = "SELECT * FROM employees WHERE activity_status = 1", nativeQuery = true)
//    List<Employee> findAllActiveUsers();
//
//    @Query(value = "SELECT * FROM employees WHERE activity_status = 0", nativeQuery = true)
//    List<Employee> findAllNonActiveUsers();
//
//    @Query(value = "SELECT * FROM employees WHERE dep_id = 12 AND activity_status = 1", nativeQuery = true)
//    List<Employee> findAllBackendEmployees();
//
//    @Query(value = "SELECT * FROM employees WHERE dep_id = 13 AND activity_status = 1", nativeQuery = true)
//    List<Employee> findAllFrontendEmployees();
//
//    @Query(value = "SELECT * FROM employees WHERE dep_id = 14 AND activity_status = 1", nativeQuery = true)
//    List<Employee> findAllDevopsEmployees();
//
//    @Query(value = "SELECT * FROM employees WHERE dep_id = 15 AND activity_status = 1", nativeQuery = true)
//    List<Employee> findAllAutomationEmployees();
//
//    @Query(value = "SELECT * FROM employees WHERE dep_id = 16 AND activity_status = 1", nativeQuery = true)
//    List<Employee> findAllFullStackEmployees();

    @Query(value = "SELECT * FROM employees WHERE dep_id = :dep_id AND activity_status = 1", nativeQuery = true)
    List<Employee> findAllDepartmentEmployees(@Param("dep_id") long dep_id);

    @Query(value = "SELECT * FROM employees WHERE activity_status = :status", nativeQuery = true)
    List<Employee> findEmpByStatus(@Param("status") String status);
}

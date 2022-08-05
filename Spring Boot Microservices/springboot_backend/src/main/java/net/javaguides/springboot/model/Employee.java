package net.javaguides.springboot.model;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "firstname")
    @NotEmpty(message = "Employee first name should not be empty")
    @Size(min = 2, message = "Employee first name should have at least 2 characters")
    private String firstname;

    @NotEmpty(message = "Employee last name should not be empty")
    @Size(min = 2, message = "Employee last name should have at least 2 characters")
    @Column(name = "lastname")
    private String lastname;

    @Email(message = "Employee email should be in proper format")
    @NotEmpty(message = "Employee email should not be empty")
    @Column(name = "email_id")
    private String emailid;

    @NotEmpty(message = "Employee department id should not be empty")
    @Column(name = "dep_id")
    private Long departmentId;

    @NotEmpty(message = "Activity status should not be empty")
    @Column(name = "activity_status")
    private String activityStatus;

}

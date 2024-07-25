package com.Employee.Mangenemt.Project.ems_backend.entity;


import jakarta.persistence.*;
import lombok.*;

//@Getter & @Setter can be replaced with @Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity  //marks the class as a persistent Java class.
@Builder
//JPA will create a table named as employees with following field properties
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "emailId",unique = true,nullable = false)
    private String email;

    // toString - To provide String representation of the object(hash code)
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

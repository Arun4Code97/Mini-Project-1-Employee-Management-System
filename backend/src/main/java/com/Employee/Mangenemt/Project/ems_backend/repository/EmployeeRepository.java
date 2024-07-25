package com.Employee.Mangenemt.Project.ems_backend.repository;

import com.Employee.Mangenemt.Project.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import java.util.Optional;

//Already extends SimpleJPARepository Class which has @Repository and @Transactional by default

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);
}

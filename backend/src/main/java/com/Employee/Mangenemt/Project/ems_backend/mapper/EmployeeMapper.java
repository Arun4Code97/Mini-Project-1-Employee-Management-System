package com.Employee.Mangenemt.Project.ems_backend.mapper;

import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import com.Employee.Mangenemt.Project.ems_backend.entity.Employee;

public class EmployeeMapper {
    //Created static methods toMapEmployeeDto,toMapEmployee for easier access
    public static EmployeeDto toMapEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }

    public static Employee toMapEmployee(EmployeeDto empDto){
    return new Employee(empDto.getId(), empDto.getFirstName(), empDto.getLastName(), empDto.getEmail());

    }
}

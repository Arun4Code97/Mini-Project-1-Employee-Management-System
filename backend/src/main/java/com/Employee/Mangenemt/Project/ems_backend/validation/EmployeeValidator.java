package com.Employee.Mangenemt.Project.ems_backend.validation;

import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidator {

    public List<String> validateCreateBookRequest(EmployeeDto employeeDto){
        List<String> errorsList = new ArrayList<>();

        if(employeeDto.getFirstName() == null || employeeDto.getFirstName().isEmpty())
            errorsList.add("firstName is required");
        if(employeeDto.getLastName() == null || employeeDto.getLastName().isEmpty())
            errorsList.add("lastName is required");
        if(employeeDto.getEmail()==null || employeeDto.getEmail().isEmpty())
            errorsList.add("email is required");

        return errorsList;
    }


}

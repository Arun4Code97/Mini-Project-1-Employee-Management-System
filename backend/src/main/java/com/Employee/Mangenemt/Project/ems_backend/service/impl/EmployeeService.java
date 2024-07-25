package com.Employee.Mangenemt.Project.ems_backend.service.impl;

import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

   public EmployeeDto createEmployee(EmployeeDto employeeDtoInput);

   public EmployeeDto getEmployeeById(Long id);

   public List<EmployeeDto> getAllEmployees();

   public EmployeeDto updateEmployee(Long id,EmployeeDto updateEmployeeDto);

   public void deleteEmployee(Long id);
}

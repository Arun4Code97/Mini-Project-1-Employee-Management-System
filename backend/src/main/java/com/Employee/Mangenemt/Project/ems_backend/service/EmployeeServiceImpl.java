package com.Employee.Mangenemt.Project.ems_backend.service;

import com.Employee.Mangenemt.Project.ems_backend.mapper.EmployeeMapper;
import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import com.Employee.Mangenemt.Project.ems_backend.entity.Employee;
import com.Employee.Mangenemt.Project.ems_backend.exception.ResourceAlreadyExistException;
import com.Employee.Mangenemt.Project.ems_backend.exception.ResourceNotFoundException;
import com.Employee.Mangenemt.Project.ems_backend.repository.EmployeeRepository;
import com.Employee.Mangenemt.Project.ems_backend.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDtoInput) {
        Employee employee = EmployeeMapper.toMapEmployee(employeeDtoInput);
        Optional<Employee> existEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existEmployee.isPresent()) {
            throw new ResourceAlreadyExistException("Could not create as employee already exist with given email : " + employee.getEmail());
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toMapEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Given Employee ID does not exist : " + id));
        return EmployeeMapper.toMapEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employeeList = employeeRepository.findAll();
        //Big step just in few lines using stream API
        //return employeeList.stream().map(employee -> EmployeeMapper.toMapEmployeeDto(employee)).collect(Collectors.toList());
        return employeeList.stream().map(EmployeeMapper::toMapEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Given employee ID does not exist : " + id));

        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.toMapEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Given employee ID does not exist : " + id));
        employeeRepository.deleteById(id);
    }
}




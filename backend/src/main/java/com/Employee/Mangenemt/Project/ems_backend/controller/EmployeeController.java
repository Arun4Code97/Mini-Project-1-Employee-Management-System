package com.Employee.Mangenemt.Project.ems_backend.controller;

import com.Employee.Mangenemt.Project.ems_backend.validation.EmployeeValidator;
import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import com.Employee.Mangenemt.Project.ems_backend.exception.ErrorResponse;
import com.Employee.Mangenemt.Project.ems_backend.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173"})
@RestController
@RequestMapping("/api/employees")
public class EmployeeController implements EmployeeAPI {


    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    EmployeeValidator employeeValidator = new EmployeeValidator();
    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto empDto)
    {

        List<String> validationErrors = employeeValidator.validateCreateBookRequest(empDto);
        if(!validationErrors.isEmpty()){

          return new ResponseEntity<>(new ErrorResponse("Validation Error",validationErrors),HttpStatus.BAD_REQUEST);
        }

            EmployeeDto savedEmployee = employeeServiceImpl.createEmployee(empDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED) ;
    }

    //Build Get Single Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeByID(@PathVariable("id") Long employeeId)
    {
        EmployeeDto employeeDto = employeeServiceImpl.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    //Build Get All Employee REST API
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeServiceImpl.getAllEmployees();
        return ResponseEntity.ok(employeeDtoList);
    }
    //Build Update Single Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee( @PathVariable Long id,
                                            @RequestBody EmployeeDto employeeDtoUpdated){
        List<String> validationErrors = employeeValidator.validateCreateBookRequest(employeeDtoUpdated);
        if(!validationErrors.isEmpty()){

            return new ResponseEntity<>(new ErrorResponse("Validation Error",validationErrors),HttpStatus.BAD_REQUEST);
        }

        EmployeeDto employeeDtoResult =employeeServiceImpl.updateEmployee(id,employeeDtoUpdated);
        return ResponseEntity.ok(employeeDtoResult);
    }

    //Build Delete Single Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeServiceImpl.deleteEmployee(id);
        return ResponseEntity.ok("Deleted employee details successfully!");
    }
}

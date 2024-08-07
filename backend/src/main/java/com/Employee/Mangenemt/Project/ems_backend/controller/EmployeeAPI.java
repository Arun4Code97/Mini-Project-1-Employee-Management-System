package com.Employee.Mangenemt.Project.ems_backend.controller;

import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import com.Employee.Mangenemt.Project.ems_backend.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeAPI {
@Tag(name = "Employee Management System", description = "The Employee REST API")
    @Operation(summary = "create an employee details",
            description = "creates an employee details where do not need to mention ID which will be automatically generated by Spring JPA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "successful create operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = EmployeeDto.class)) }),
            @ApiResponse(responseCode = "400",description = "Validation error in given DTO and responses HttpStatus.BAD_REQUEST",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "302",description = "employee exists already",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)) })
               })
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto empDto);



@Tag(name = "GET",description = "GET methods of employee API")
    @Operation(
            summary = "Get single employee details by using Id",
            description = "fetches a employee details from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful get operation",
                         content = { @Content(mediaType="application/json" ,schema = @Schema(implementation = EmployeeDto.class))
                                   }
                        ),
            @ApiResponse(responseCode = "404",description = "employee does not exist",
            content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))
                      })  }
                 )
    public ResponseEntity<EmployeeDto> getEmployeeByID(@Parameter(
        description = "ID of employee to be retrieved",
        required = true)@PathVariable("id") Long employeeId);


@Tag(name = "GET",description = "GET methods of employee API")
    @Operation(
            summary = "Get all employee details",
            description = "fetches all employee details from data source")
    @ApiResponse(responseCode = "200", description = "successful get all employees operation",
                 content = {@Content(mediaType = "application/json",schema = @Schema(implementation = EmployeeDto.class))})
    public ResponseEntity<List<EmployeeDto>> getAllEmployees();



@Tag(name = "Employee Management System", description = "The Employee REST API")
    @Operation(summary = "update a employee details",
               description = "updates an employee details by using Id")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200",description = "successful update operation",
                          content = { @Content(mediaType = "application/json",schema = @Schema(implementation = EmployeeDto.class)) }),
             @ApiResponse(responseCode = "400",description = "Validation error in given DTO and responses HttpStatus.BAD_REQUEST",
                          content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "404",description = "employee does not exist",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)) })
                        })
    public ResponseEntity<?> updateEmployee(@Parameter(
        description = "ID of employee to be updated",
        required = true) @PathVariable Long id,
                                            @RequestBody EmployeeDto employeeDtoUpdated);


@Tag(name = "Employee Management System", description = "The Employee REST API")
    @Operation(summary = "deletes an employee details",
              description = "deletes an employee details by using Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful delete operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = EmployeeDto.class)) }),
            @ApiResponse(responseCode = "404",description = "employee does not exist",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)) })
    })
    public ResponseEntity<?> deleteEmployee(@Parameter(
        description = "ID of employee to be deleted",
        required = true) @PathVariable("id") Long id);

}

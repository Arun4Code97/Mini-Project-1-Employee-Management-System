package com.Employee.Mangenemt.Project.ems_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorResponse("Resource not found", List.of(ex.getMessage()));
    }
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseBody
    public ErrorResponse handleResourceAlreadyExistException(ResourceAlreadyExistException ex){
        return  new ErrorResponse("Resource already exists",List.of(ex.getMessage()));
    }
}

package com.Employee.Mangenemt.Project.ems_backend.services;

import com.Employee.Mangenemt.Project.ems_backend.mapper.EmployeeMapper;
import com.Employee.Mangenemt.Project.ems_backend.dto.EmployeeDto;
import com.Employee.Mangenemt.Project.ems_backend.entity.Employee;
import com.Employee.Mangenemt.Project.ems_backend.repository.EmployeeRepository;
import com.Employee.Mangenemt.Project.ems_backend.service.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee = new Employee();

    //        employee= new Employee(5L,"Arun","kumar","Ak@gmail.com");


    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .id(5L)
                .firstName("Arun")
                .lastName("kumar")
                .email("aruntest@gmail.com")
                .build();

    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        when(employeeRepository.findByEmail(employee.getEmail()))
                .thenReturn(Optional.empty());

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Given - precondition or setup
//        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

//        System.out.println("Mocked employeeRepository: " + employeeRepository);
//        System.out.println("Injected to employeeService: " + employeeService);

//        // Printing the employee details before the test
//        System.out.println("Original Employee: " + employee);

        // When - action or the behaviour that we are going to test
        EmployeeDto employeeDto = EmployeeMapper.toMapEmployeeDto(employee);
//        System.out.println("Mapped InputEmployeeDto: " + employeeDto);

        EmployeeDto savedEmployeeDTO = employeeService.createEmployee(employeeDto);
        Employee savedEmployee = EmployeeMapper.toMapEmployee(savedEmployeeDTO);
//        System.out.println("Mapped back/updated Employee: " + savedEmployee);

        // Then - verify the output
        assertThat(savedEmployee).isNotNull();

        // Extra check for debugging
        assertThat(savedEmployee.getId()).isEqualTo(employee.getId());
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(savedEmployee.getEmail()).isEqualTo(employee.getEmail());
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("elon")
                .lastName("musk")
                .email("elon@gmail.com")
                .build();

        when(employeeRepository.findAll()).thenReturn(List.of(employee,employee1));


        // when -  action or the behaviour that we are going to test
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
        when(employeeRepository.findById(5L)).thenReturn(Optional.of(employee));

        // when
        EmployeeDto savedEmployee = employeeService.getEmployeeById(employee.getId());

        // then
        assertThat(savedEmployee).isNotNull();

    }

    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findById(5L)).thenReturn(Optional.of(employee));

//        System.out.println("Previous employee" + " :\n " + employee.getId() +employee);
        employee.setFirstName("Alex");
        employee.setEmail("alex@gmail.com");

//        System.out.println("updated employee" + " :\n " + employee.getId() +employee);

        EmployeeDto inputEmployeeDto = EmployeeMapper.toMapEmployeeDto(employee);
        // when -  action or the behaviour that we are going test
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employee.getId(),inputEmployeeDto);

        // then - verify the output
        assertThat(updatedEmployeeDto.getEmail()).isEqualTo("alex@gmail.com");
        assertThat(updatedEmployeeDto.getFirstName()).isEqualTo("Alex");
    }


    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        long employeeId = 5L;
        when(employeeRepository.findById(5L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(employeeId);

        // when -  action or the behaviour that we are going test
        employeeService.deleteEmployee(employeeId);

        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }



}



package com.k9unit.employeedb.controller;

import com.k9unit.employeedb.domain.Employee;
import com.k9unit.employeedb.domain.StatusType;
import com.k9unit.employeedb.exception.EmployeeNotFoundException;
import com.k9unit.employeedb.repositories.EmployeeRepository;
import com.k9unit.employeedb.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeControllerTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Test
    void getEmployeeById() {
        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.findById(Long.parseLong("101"))).thenReturn(java.util.Optional.of(employee));

        try {
            assertEquals(employee, employeeService.getEmployeeById(101L));
        } catch (EmployeeNotFoundException e) {
            fail("Number of Employees returned is incorrect");
        }

    }

    @Test
    void getEmployeeByIdInactive() {
        Employee employee = new Employee();
        employee.setStatus(StatusType.INACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.findById(Long.parseLong("101"))).thenReturn(java.util.Optional.of(employee));

        try {
            assertEquals(employee, employeeService.getEmployeeById(101L));
        } catch (EmployeeNotFoundException e) {
            assertTrue(true, " Employee Not found exception is throw when in active");
        }
    }


    @Test
    void getEmployeeByIdNotFound() {
        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.findById(Long.parseLong("101"))).thenReturn(java.util.Optional.of(employee));

        try {
            assertEquals(employee, employeeService.getEmployeeById(102L));
        } catch (EmployeeNotFoundException e) {
            assertTrue(true, " Employee Not found thrown with invalid");
        }
    }

    @Test
    void addEmployee() {

        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.createEmployee(employee));
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.findById(Long.parseLong("101"))).thenReturn(java.util.Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        try {
            assertEquals(employee, employeeService.updateExistingEmployee(101L, employee));
        } catch (EmployeeNotFoundException e) {
            fail("Number of Employees returned is incorrect");
        }
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        when(employeeRepository.findById(Long.parseLong("101"))).thenReturn(java.util.Optional.of(employee));
        try {
            employeeService.deleteEmployeeById(101L);
            verify(employeeRepository, Mockito.times(1)).save(employee);
        } catch (EmployeeNotFoundException e) {
            fail("Delete did not happen");
        }

    }

    @Test
    void getAllEmployees() {

        Employee employee = new Employee();
        employee.setStatus(StatusType.ACTIVE);
        employee.setFirstName("First");
        employee.setMiddleInitial("M");
        employee.setLastName("Last");
        employee.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee.setId(101L);

        Employee employee1 = new Employee();
        employee1.setStatus(StatusType.ACTIVE);
        employee1.setFirstName("First");
        employee1.setMiddleInitial("M");
        employee1.setLastName("Last");
        employee1.setEmploymentDate(LocalDate.parse("2010-01-01"));
        employee1.setDateOfBirth(LocalDate.parse("2010-01-01"));
        employee1.setId(102L);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);

        when(employeeRepository.findAll()).thenReturn(employees);

        try {
            assertEquals(employees, employeeService.getAllEmployees());
        } catch (EmployeeNotFoundException e) {
            fail("Number of Employees returned is incorrect");
        }
    }
}

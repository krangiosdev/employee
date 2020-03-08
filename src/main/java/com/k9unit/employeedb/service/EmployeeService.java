package com.k9unit.employeedb.service;

import com.k9unit.employeedb.domain.Employee;
import com.k9unit.employeedb.exception.EmployeeNotFoundException;

import java.util.List;


public interface EmployeeService {

    List<Employee> getAllEmployees() throws EmployeeNotFoundException;

    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;

    Employee createEmployee(Employee employee);

    Employee updateExistingEmployee(Long id, Employee employee) throws EmployeeNotFoundException;

    void deleteEmployeeById(Long id) throws EmployeeNotFoundException;

}

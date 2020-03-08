package com.k9unit.employeedb.controller;


import com.k9unit.employeedb.domain.Employee;
import com.k9unit.employeedb.exception.EmployeeNotFoundException;
import com.k9unit.employeedb.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) throws EmployeeNotFoundException {
        log.debug("Get Employee by Id: {}", id);
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        log.debug("Add employee: {}", employee.toString());
        return employeeService.createEmployee(employee);
    }

    @PutMapping("employee/{id}")
    public Employee updateEmployee(@PathVariable long id, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        log.debug("Update employee by Id: {}", id);
        return employeeService.updateExistingEmployee(id, employee);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        log.debug("Delete employee by Id: {}", id);
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
        return employeeService.getAllEmployees();
    }

}

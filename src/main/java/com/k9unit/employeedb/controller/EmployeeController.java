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

    /**
     * Get a specific employee with the Id
     *
     * @param id Employee Id
     * @return if employee found returns employee or throws exception
     * @throws EmployeeNotFoundException If employee not found
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) throws EmployeeNotFoundException {
        log.debug("Get Employee by Id: {}", id);
        return employeeService.getEmployeeById(id);
    }

    /**
     * Add a new employee
     *
     * @param employee Employee object with specified parameters
     * @return employee object created
     */
    @PostMapping("/employee")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        log.debug("Add employee: {}", employee.toString());
        return employeeService.createEmployee(employee);
    }

    /**
     * Updates exciting employee
     *
     * @param id       employee id
     * @param employee employee object with change except status change
     * @return udpate eomployee object
     * @throws EmployeeNotFoundException if employee cannot be found to perform update
     */
    @PutMapping("employee/{id}")
    public Employee updateEmployee(@PathVariable long id, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        log.debug("Update employee by Id: {}", id);
        return employeeService.updateExistingEmployee(id, employee);
    }

    /**
     * Delete an Employee (Making employee INACTIVE)
     *
     * @param id employee id
     * @throws EmployeeNotFoundException if the employee cannot be found to delete
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("employee/{id}")
    public void deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        log.debug("Delete employee by Id: {}", id);
        employeeService.deleteEmployeeById(id);
    }

    /**
     * Gets all the ACTIVE status employees
     *
     * @return List of Employees with details
     * @throws EmployeeNotFoundException if no employee is found
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
        return employeeService.getAllEmployees();
    }

}

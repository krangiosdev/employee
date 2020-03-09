package com.k9unit.employeedb.service.impl;

import com.k9unit.employeedb.config.MessageResolver;
import com.k9unit.employeedb.domain.Employee;
import com.k9unit.employeedb.domain.StatusType;
import com.k9unit.employeedb.exception.EmployeeNotFoundException;
import com.k9unit.employeedb.repositories.EmployeeRepository;
import com.k9unit.employeedb.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for employees
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MessageResolver getMessage;

    @Override
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
        List<Employee> employees = employeeRepository.findAll().stream().filter(employee -> employee.getStatus().equals(StatusType.ACTIVE)).collect(Collectors.toList());
        if (employees.isEmpty()) {
            log.warn("Get AllEmployees call failed and returned empty");
            throw new EmployeeNotFoundException(getMessage.resolve("global.employeeNotFoundException.detail"));
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findById(id).filter(employee -> employee.getStatus().equals(StatusType.ACTIVE));
        if (employee1.isPresent()) {
            return employee1.get();
        } else {
            log.warn("Get employee call failed and returned empty");
            throw new EmployeeNotFoundException(getMessage.resolve("global.employeeNotFoundException.detail"));
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateExistingEmployee(Long id, Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()) {
            employee.setId(id);
            //Cannot change employee to Inactive in this call
            employee.setStatus(StatusType.ACTIVE);
            return employeeRepository.save(employee);
        } else {
            log.error("No employee with ID:{} to update", id);
            throw new EmployeeNotFoundException(getMessage.resolve("global.employeeNotFoundException.detail"));
        }
    }

    @Override
    public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()) {
            employee1.get().setStatus(StatusType.INACTIVE);
            employeeRepository.save(employee1.get());
        } else {
            log.error("No employee with ID:{} to delete", id);
            throw new EmployeeNotFoundException(getMessage.resolve("global.employeeNotFoundException.detail"));
        }
    }
}

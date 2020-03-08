package com.k9unit.employeedb.exception;

public class EmployeeNotFoundException extends Exception {


    private static final long serialVersionUID = -8717842111361169222L;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

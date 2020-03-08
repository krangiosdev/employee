package com.k9unit.employeedb.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.k9unit.employeedb.config.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @Autowired
    MessageResolver getMessage;

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomErrorResponse invalidLocalDate(DateTimeParseException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setTitle(getMessage.resolve("global.dateParseException.title"));
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errors.add(getMessage.resolve("global.dateParseException.detail"));
        customErrorResponse.setDetail(errors);
        customErrorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return customErrorResponse;
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomErrorResponse invalidParsing(InvalidFormatException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setTitle(getMessage.resolve("global.invalidFormatException.title"));
        List<String> errors = new ArrayList<>();
        errors.add(ex.getCause().getMessage());
        errors.add(getMessage.resolve("global.invalidFormatException.detail"));
        customErrorResponse.setDetail(errors);
        customErrorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return customErrorResponse;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorResponse employeeNotFound(EmployeeNotFoundException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setTitle(getMessage.resolve("global.employeeNotFoundException.title"));
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        customErrorResponse.setDetail(errors);
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return customErrorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorResponse invalidParams(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setDetail(errors);
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customErrorResponse.setTitle(getMessage.resolve("global.MethodArgumentNotValidException.title"));
        return customErrorResponse;
    }
}

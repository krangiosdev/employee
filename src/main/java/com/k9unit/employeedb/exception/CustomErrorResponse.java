package com.k9unit.employeedb.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomErrorResponse {

    private int status;
    private String title;
    private List<String> detail;

}

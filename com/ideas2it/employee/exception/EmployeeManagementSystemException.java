package com.ideas2it.employee.exception;

public class EmployeeManagementSystemException extends Exception {

    private String  errorCode;

    public EmployeeManagementSystemException(String exception, String errorCode) {
    super(exception);
    this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
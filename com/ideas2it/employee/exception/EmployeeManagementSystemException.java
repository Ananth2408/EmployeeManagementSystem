package com.ideas2it.employee.exception;

/**
 * Used handle the exception in this application.
 * @version 4.0 28-09-2022.
 * @author  Ananth K.
 */
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
package com.ideas2it.employee.exception;

/**
 * Used handle the exception in this application.
 * @version 4.1 10-10-2022.
 * @author  Ananth K.
 */
public class EMSException extends Exception {

    private String  errorCode;

    /**
     * This is used to throw the error from the methods.
     * @param exception throwed
     * @param errorcode used to refer the exception.
     */
    public EMSException(String exception, String errorCode) {
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
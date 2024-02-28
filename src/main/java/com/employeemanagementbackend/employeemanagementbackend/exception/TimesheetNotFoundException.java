package com.employeemanagementbackend.employeemanagementbackend.exception;

public class TimesheetNotFoundException extends Exception{
    public TimesheetNotFoundException() {
    }

    public TimesheetNotFoundException(String message) {
        super(message);
    }

    public TimesheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimesheetNotFoundException(Throwable cause) {
        super(cause);
    }

    public TimesheetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

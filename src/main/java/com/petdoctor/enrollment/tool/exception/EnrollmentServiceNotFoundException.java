package com.petdoctor.enrollment.tool.exception;

public class EnrollmentServiceNotFoundException extends RuntimeException {

    public EnrollmentServiceNotFoundException() {
    }

    public EnrollmentServiceNotFoundException(String message) {
        super(message);
    }
}

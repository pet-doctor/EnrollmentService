package com.petdoctor.enrollment.tool.exception;

public class EnrollmentNotFoundException extends RuntimeException{

    public EnrollmentNotFoundException() {
        super();
    }

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}

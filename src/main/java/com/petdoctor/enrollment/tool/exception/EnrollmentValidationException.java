package com.petdoctor.enrollment.tool.exception;

public class EnrollmentValidationException extends RuntimeException {

    public EnrollmentValidationException() {
        super();
    }

    public EnrollmentValidationException(String message) {
        super(message);
    }
}

package com.edu.ctu.thesis.exceptions;

public class InvalidAccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}

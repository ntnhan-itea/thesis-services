package com.edu.ctu.thesis.exceptions;

public class StringBlankException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public StringBlankException() {
        super();
    }

    public StringBlankException(String valueName) {
        super(valueName);
    }

    public StringBlankException(String valueName, Throwable cause) {
        super(valueName, cause);
    }
}

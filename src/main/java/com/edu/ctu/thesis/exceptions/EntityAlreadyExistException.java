package com.edu.ctu.thesis.exceptions;

public class EntityAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public EntityAlreadyExistException() {
        super();
    }

    public EntityAlreadyExistException(String entityName) {
        super(entityName);
    }

    public EntityAlreadyExistException(String entityName, Throwable cause) {
        super(entityName, cause);
    }
}

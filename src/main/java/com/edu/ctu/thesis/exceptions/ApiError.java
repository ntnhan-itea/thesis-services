package com.edu.ctu.thesis.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Setter
public class ApiError {

    private int status;

    private String message;

    private List<String> messages;

    private LocalDateTime timestamp;

    private String path;

    Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ApiError(int status, List<String> messages, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.messages = messages;
        this.path = path;
    }

    public ApiError(int status, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.path = path;
    }
}
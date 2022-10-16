package com.edu.ctu.thesis;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiError {

    int status;

    String message;

    LocalDateTime timestamp;

    String path;

    Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
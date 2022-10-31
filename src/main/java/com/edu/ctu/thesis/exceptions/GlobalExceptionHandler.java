package com.edu.ctu.thesis.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiError error = new ApiError(400, request.getServletPath());
        BindingResult bindingResult = exception.getBindingResult();
        // Map<String, String> validationErrors = new HashMap<>();
        String message = "{";
        List<String> messages = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorField = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            messages.add(errorMessage);

            message += errorMessage + "; ";
            log.error(errorField + ": " + errorMessage);
        }
        message = message.trim();
        message += "}";

        error.setMessage(message);
        error.setMessages(messages);
        return error;
    }

    @ExceptionHandler(value = { EntityNotFound.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleExceptionNotFoundEntity(EntityNotFound e, HttpServletRequest request) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleExceptionUnknown(Exception e, HttpServletRequest request) {
        log.error("Something wrong: ", e);
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error!", request.getServletPath());
    }
}

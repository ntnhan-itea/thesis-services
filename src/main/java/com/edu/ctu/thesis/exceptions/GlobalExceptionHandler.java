package com.edu.ctu.thesis.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
        String message = "";
        List<String> messages = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorField = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            messages.add(errorMessage);

            message += errorMessage + ". ";
            log.error(errorField + ": " + errorMessage);
        }
        message = message.trim();
        message += "";

        error.setMessage(message);
        error.setMessages(messages);
        return error;
    }

    @ExceptionHandler(value = { EntityNotFound.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleExceptionNotFoundEntity(EntityNotFound e, HttpServletRequest request) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(value = { InvalidAccountException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidAccountException(Exception e, HttpServletRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(value = { EntityAlreadyExistException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleEntityAlreadyExistException(EntityAlreadyExistException e, HttpServletRequest request) {
        String message = "Entity is already exist in Database";
        if (StringUtils.isNotBlank(e.getMessage())) {
            message = "Entity [" + e.getMessage() + "] is already exist in Database";
        }

        return new ApiError(HttpStatus.BAD_REQUEST.value(), message, request.getServletPath());
    }

    @ExceptionHandler(value = { StringBlankException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleStringBlankException(StringBlankException e, HttpServletRequest request) {
        String message = "Value input should not be blank";
        if (StringUtils.isNotBlank(e.getMessage())) {
            message = "[" + e.getMessage() + "] input should not be blank";
        }

        return new ApiError(HttpStatus.BAD_REQUEST.value(), message, request.getServletPath());
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ApiError handleHttpRequestMethodNotSupportedException(Exception e,
            HttpServletRequest request) {
        return new ApiError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage(),
                request.getServletPath());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleExceptionUnknown(Exception e, HttpServletRequest request) {
        log.error("Something wrong: ", e);
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong!",
                request.getServletPath());
    }
}

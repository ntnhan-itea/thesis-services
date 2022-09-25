package com.edu.ctu.thesis;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    // @Order(Ordered.HIGHEST_PRECEDENCE)
    // @ExceptionHandler(value = { ConstraintViolationException.class })
    // public ResponseEntity<Object> handleConstraint(ConstraintViolationException
    // ex,
    // WebRequest request) {

    // ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
    // "Record still have reference from other table",
    // request.getDescription(false));
    // return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    // }

    // @Order(Ordered.LOWEST_PRECEDENCE)
    // @ExceptionHandler(value = { Exception.class })
    // public final ResponseEntity<Object> handleAllExceptions(Exception ex,
    // WebRequest request) {
    // ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
    // ex.getMessage() + " Custom Error",
    // request.getDescription(false));
    // return new ResponseEntity(exceptionResponse,
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody List<String> handleConstraintViolationException(ConstraintViolationException ex) {

        return ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessageTemplate())
                .collect(Collectors.toList());
    }

    // @ExceptionHandler({ IllegalArgumentException.class,
    // IllegalStateException.class })
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public @ResponseBody String
    // handleConstraintViolationException(IllegalArgumentException ex) {
    // return ex.getMessage();
    // }

}
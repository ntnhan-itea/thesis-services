package com.edu.ctu.thesis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiError error = new ApiError(400, "", request.getServletPath());
        BindingResult bindingResult = exception.getBindingResult();
        // Map<String, String> validationErrors = new HashMap<>();
        String message = "{";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {

            message += fieldError.getField() + ": " + fieldError.getDefaultMessage() + "; ";
        }
        message = message.trim();
        message += "}";

        error.setMessage(message);
        return error;
    }

    
//     @ExceptionHandler(ConstraintViolationException.class)
//     @ResponseStatus(HttpStatus.BAD_REQUEST)
//     public @ResponseBody String handleConstraintViolationException(ConstraintViolationException ex) {

//         return ex.getMessage();
//     }
}

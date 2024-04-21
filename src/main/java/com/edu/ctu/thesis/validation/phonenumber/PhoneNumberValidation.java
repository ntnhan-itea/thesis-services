package com.edu.ctu.thesis.validation.phonenumber;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberValidation {
    String message() default "Phone number should be 10 digits and start with '0'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

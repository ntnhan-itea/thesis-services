package com.edu.ctu.thesis.seafood.admin.validation.username;

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
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsernameValidation {
    String message() default "Username is already exist in Database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

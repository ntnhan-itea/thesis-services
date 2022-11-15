package com.edu.ctu.thesis.seafood.admin.validation.username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.ctu.thesis.seafood.admin.AdminService;

import lombok.Setter;

@Setter
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameValidation, String> {

    @Autowired
    AdminService adminService;

    // @Override
    // public void initialize(UniqueUsernameValidation constraintAnnotation) {
    // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    // }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (StringUtils.isBlank(username)) {
            context.buildConstraintViolationWithTemplate("Username should not be blank").addConstraintViolation();
            return false;
        }

        username = username.trim();
        boolean isExistUsername = this.adminService.isExistUsername(username);
        // boolean isExistUsername = false;
        if (isExistUsername) {
            context.buildConstraintViolationWithTemplate("Username [" + username + "] is already exist in Database")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}

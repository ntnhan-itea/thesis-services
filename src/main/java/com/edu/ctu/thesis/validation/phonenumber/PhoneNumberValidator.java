package com.edu.ctu.thesis.validation.phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {

    private static final String PATTERN_PHONE_NUMBER_START_WITH_ZERO = "^0\\d{9}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(phoneNumber)) {
            return false;
        }

        return phoneNumber.trim().matches(PATTERN_PHONE_NUMBER_START_WITH_ZERO);
    }
    
}

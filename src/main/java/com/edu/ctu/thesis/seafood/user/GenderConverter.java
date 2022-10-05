package com.edu.ctu.thesis.seafood.user;

import java.util.Arrays;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        if (gender != null) {
            return gender.getValue();
        }
        return Gender.UNKNOWN.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer value) {
        if (value == null) {
            return Gender.UNKNOWN;
        }

        Gender[] genders = Gender.values();
        return Arrays.stream(genders).filter(e -> e.getValue() == value).findFirst().orElse(Gender.UNKNOWN);
    }

}

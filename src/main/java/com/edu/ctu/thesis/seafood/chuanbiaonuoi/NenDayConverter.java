package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import java.util.Arrays;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NenDayConverter implements AttributeConverter<NenDay, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NenDay nenDay) {
        if (nenDay == null) {
            return null;
        }
        return nenDay.getValue();
    }

    @Override
    public NenDay convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        NenDay[] nenDays = NenDay.values();
        return Arrays.stream(nenDays).filter(e -> e.getValue() == value).findFirst().orElse(null);
    }

}

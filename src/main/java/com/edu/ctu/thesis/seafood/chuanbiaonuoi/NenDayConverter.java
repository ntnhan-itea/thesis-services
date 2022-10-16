package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import java.util.Arrays;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

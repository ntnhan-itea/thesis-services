package com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.AttributeConverter;

public class TinhTrangNuocConverter implements AttributeConverter<TinhTrangNuoc, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TinhTrangNuoc arg0) {
        Integer value = TinhTrangNuoc.KHONG_XAC_DINH.getValue();
        if (Objects.nonNull(arg0)) {
            value = arg0.getValue();
        }
        return value;
    }

    @Override
    public TinhTrangNuoc convertToEntityAttribute(Integer arg0) {
        TinhTrangNuoc[] tinhTrangNuocs = TinhTrangNuoc.values();

        return Arrays.stream(tinhTrangNuocs)
                .filter(each -> each.getValue() == arg0)
                .findFirst().orElse(TinhTrangNuoc.KHONG_XAC_DINH);
    }

}

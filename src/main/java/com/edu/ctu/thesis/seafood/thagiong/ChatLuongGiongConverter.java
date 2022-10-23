package com.edu.ctu.thesis.seafood.thagiong;

import java.util.Arrays;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatLuongGiongConverter implements AttributeConverter<ChatLuongGiong, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChatLuongGiong chatLuongGiong) {
        return chatLuongGiong == null ? null : chatLuongGiong.getValue();
    }

    @Override
    public ChatLuongGiong convertToEntityAttribute(Integer value) {
        if(value == null) {
            return null;
        }
        return Arrays.stream(ChatLuongGiong.values()).filter(e -> e.getValue() == value).findFirst().orElse(null);
    }

}

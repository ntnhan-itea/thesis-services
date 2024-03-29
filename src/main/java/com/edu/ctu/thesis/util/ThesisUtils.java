package com.edu.ctu.thesis.util;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

public class ThesisUtils {

    private ThesisUtils() {}

    public static String encodeBase64(String input) {
        if(StringUtils.isBlank(input)) {
            return StringUtils.EMPTY;
        }

        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        return encodedString;
    }

    public static String decodeBase64(String input) {
        if(StringUtils.isBlank(input)) {
            return StringUtils.EMPTY;
        }

        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }

}

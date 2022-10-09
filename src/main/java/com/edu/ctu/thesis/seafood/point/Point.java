package com.edu.ctu.thesis.seafood.point;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

// include fields null --> dont show to response Json
//@JsonInclude(value = Include.NON_NULL) 
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {

    private static final String COMMA = ",";

    public Point(List<Double> coordinates, String type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    private List<Double> coordinates;

    private String type;

    @JsonIgnore
    public String getPointString() {
        return StringUtils.join(coordinates, COMMA);
    }
}

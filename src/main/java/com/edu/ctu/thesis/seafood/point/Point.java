package com.edu.ctu.thesis.seafood.point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// include fields null --> dont show to response Json
//@JsonInclude(value = Include.NON_NULL) 
@Entity
@Table(name = "tbl_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {

    @JsonIgnore
    private static final String SEMI_COLON = ";";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Transient
    @NotNull(message = "Coordinates list should not be missing")
    private List<Double> coordinates;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "coordinate", nullable = false)
    @JsonIgnore
    private String coordinatesInDB;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vung_nuoi_id")
    private VungNuoi vungNuoi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ao_nuoi_id")
    private AoNuoi aoNuoi;

    public void copy(Point point) {
        this.type = point.type;
    }

    @PrePersist
    @PreUpdate
    private void convertCoordinateToCoordinateDB() {
        if (!CollectionUtils.isEmpty(this.coordinates)) {
            this.coordinatesInDB = StringUtils.join(this.coordinates, SEMI_COLON);
        }
    }

    public List<Double> getCoordinates() {
        if (StringUtils.isBlank(this.coordinatesInDB)) {
            return Collections.emptyList();
        }
        List<String> tempList = Arrays.asList(this.coordinatesInDB.split(SEMI_COLON));
        return tempList.stream().filter(Objects::nonNull).map(Double::parseDouble).collect(Collectors.toList());
    }

}

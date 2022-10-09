package com.edu.ctu.thesis.seafood.vungnuoi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vung_nuoi")
// @Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = { "id" })
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonInclude(value = Include.NON_NULL)
public class VungNuoi extends Validity {

    private static final String SEMI_COLON = ";";
    private static final String COMMA = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_vung_nuoi", nullable = false)
    @NotBlank(message = "Ten vung nuoi khong duoc bo trong!")
    private String tenVungNuoi;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "mo_ta")
    private String moTa;

    @OneToMany(mappedBy = "vungNuoi", cascade = CascadeType.ALL)
    private List<AoNuoi> aoNuois;

    @Transient
    @JsonProperty(access = Access.READ_WRITE)
    private List<Point> listOfPoint;

    @Column(name = "points", columnDefinition = "TEXT")
    @JsonIgnore
    private String points;

    @Column(name = "point_type")
    @JsonIgnore
    private String type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trai_nuoi_id", nullable = false)
    private TraiNuoi traiNuoi;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    @JsonIgnore
    @Embedded
    private Audit audit;

    public void convertListPointsToString() {
        if (!CollectionUtils.isEmpty(this.listOfPoint)) {
            this.type = this.listOfPoint.stream().filter(Objects::nonNull).findFirst().map(Point::getType).orElse(null);

            List<String> coordinates = this.listOfPoint.stream()
                    .filter(Objects::nonNull)
                    .map(Point::getPointString)
                    .collect(Collectors.toList());
            this.points = StringUtils.join(coordinates, SEMI_COLON);
        } else {
            this.type = null;
            this.points = null;
        }
    }

    public List<Point> getListOfPoint() {
        if (StringUtils.isBlank(this.points)) {
            return Collections.emptyList();
        }

        List<String> tempListPoint = Arrays.asList(this.points.split(SEMI_COLON));

        List<Point> result = tempListPoint.stream().filter(Objects::nonNull).map(e -> {
            List<String> pointString = Arrays.asList(e.split(COMMA));
            List<Double> coordinates = pointString.stream()
                    .filter(Objects::nonNull)
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());
            return new Point(coordinates, this.type);
        }).collect(Collectors.toList());

        return result;
    }

    public void copy(VungNuoi vungNuoi) {
        this.tenVungNuoi = vungNuoi.tenVungNuoi;
        this.diaChi = vungNuoi.diaChi;
        this.moTa = vungNuoi.moTa;
        this.listOfPoint = new ArrayList<>(vungNuoi.listOfPoint);
    }

    public void clear() {
        this.diaChi = null;
        this.moTa = null;
        this.listOfPoint = null;
        this.aoNuois = null;
        // this.traiNuoi = null;
        // this.user = null;
        this.audit = null;
    }

    @JsonIgnore
    public boolean isValid() {
        if (StringUtils.isBlank(this.tenVungNuoi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VungNuoi [id=" + id + ", tenVungNuoi=" + tenVungNuoi + ", diaChi=" + diaChi + ", moTa=" + moTa
                + ", aoNuois=" + aoNuois + ", traiNuoi=" + traiNuoi + ", user=" + user + "]";
    }

    @PrePersist
    private void logNewUserAttempt() {
        // this.convertListPointsToString();

        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();

        this.audit.setCreationTime(now);
        this.audit.setModificationTime(now);
    }

    @PreUpdate
    private void logUserUpdateAttempt() {
        // this.convertListPointsToString();

        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();
        this.audit.setModificationTime(now);
    }

}

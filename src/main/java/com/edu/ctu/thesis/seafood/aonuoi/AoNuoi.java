package com.edu.ctu.thesis.seafood.aonuoi;

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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
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
@Table(name = "ao_nuoi")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AoNuoi extends Validity {

    private static final String SEMI_COLON = ";";
    private static final String COMMA = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "ten_ao", nullable = false)
    @NotBlank(message = "Ten ao khong duoc bo trong")
    private String tenAo;

    @Column(name = "do_sau")
    private Float doSau;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "dia_chi")
    private String diaChi;

    @Transient
    @JsonProperty(access = Access.READ_WRITE)
    private List<Point> listOfPoint;

    @Column(name = "points", columnDefinition = "TEXT")
    @JsonIgnore
    private String points;

    @Column(name = "point_type")
    @JsonIgnore
    private String type;

    @OneToMany(mappedBy = "aoNuoi", cascade = CascadeType.ALL)
    private List<NhatKy> listOfNhatKy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vung_nuoi_id", nullable = false)
    private VungNuoi vungNuoi;

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
            this.listOfPoint = null;
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

    @PrePersist
    private void logNewUserAttempt() {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();

        this.audit.setCreationTime(now);
        this.audit.setModificationTime(now);
    }

    @PreUpdate
    private void logUserUpdateAttempt() {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();
        this.audit.setModificationTime(now);
    }

    public void copy(AoNuoi aoNuoi) {
        this.tenAo = aoNuoi.tenAo;
        this.doSau = aoNuoi.doSau;
        this.moTa = aoNuoi.moTa;
        this.diaChi = aoNuoi.diaChi;
        this.listOfPoint = new ArrayList<>(aoNuoi.listOfPoint);
    }

    @Override
    public String toString() {
        return "AoNuoi [id=" + id + ", tenAo=" + tenAo + ", doSau=" + doSau + ", moTa=" + moTa + ", diaChi=" + diaChi
                + ", user=" + user + ", vungNuoi=" + vungNuoi + "]";
    }

}

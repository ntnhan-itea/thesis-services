package com.edu.ctu.thesis.seafood.vungnuoi;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.valididy.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vung_nuoi")
@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class VungNuoi extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_vung_nuoi", nullable = false, unique = true)
    @NotBlank(message = "Ten vung nuoi khong duoc bo trong!")
    private String tenVungNuoi;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "geo_json")
    private String geoJson;

    @OneToMany(mappedBy = "vungNuoi", cascade = CascadeType.ALL)
    @JsonProperty("listOfPoint")
    private List<Point> listOfPoint;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trai_nuoi_id", nullable = false)
    private TraiNuoi traiNuoi;

    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(VungNuoi vungNuoi) {
        this.tenVungNuoi = vungNuoi.tenVungNuoi;
        this.diaChi = vungNuoi.diaChi;
        this.moTa = vungNuoi.moTa;
        this.geoJson = vungNuoi.geoJson;
    }

    @JsonIgnore
    public boolean isValid() {
        if (StringUtils.isBlank(this.tenVungNuoi)) {
            return false;
        }
        return true;
    }

}

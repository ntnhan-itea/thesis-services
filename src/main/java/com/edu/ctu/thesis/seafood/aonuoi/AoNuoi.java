package com.edu.ctu.thesis.seafood.aonuoi;

import java.time.LocalDateTime;
import java.util.List;

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
import javax.validation.constraints.NotBlank;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ao_nuoi")
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AoNuoi extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "aoNuoi", cascade = CascadeType.ALL)
    private List<Point> listOfPoint;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vung_nuoi_id", nullable = false)
    private VungNuoi vungNuoi;

    @JsonIgnore
    @Embedded
    private Audit audit;

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
    }

}

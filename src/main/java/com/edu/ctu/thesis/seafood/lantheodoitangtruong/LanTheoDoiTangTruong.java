package com.edu.ctu.thesis.seafood.lantheodoitangtruong;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "lan_theo_doi_tang_truong")
@Data
@EqualsAndHashCode(callSuper = false)
@Validated
public class LanTheoDoiTangTruong extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "lan_kiem_tra")
    private Integer lanKiemTra;

    @Column(name = "ngay_kiem_tra")
    private LocalDate ngayKiemTra;

    @Column(name = "tuoi_tom")
    private Integer tuoiTom;

    @Column(name = "mat_do_trung_binh")
    private Integer matDoTrungBinh;

    @Column(name = "khoi_luong_trung_binh")
    private Float khoiLuongTrungBinh;

    @Column(name = "toc_do_sinh_truong")
    private Float tocDoSinhTruong;

    @Column(name = "ty_le_song")
    private Float tyLeSong;

    @Column(name = "sinh_khoi")
    private Float sinhKhoi;

    @Column(name = "note")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "nhat_ky_id", nullable = false)
    @JsonIgnore
    private NhatKy nhatKy;

    @Valid
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        this.lanKiemTra = lanTheoDoiTangTruong.lanKiemTra;
        this.ngayKiemTra = lanTheoDoiTangTruong.ngayKiemTra;
        this.tuoiTom = lanTheoDoiTangTruong.tuoiTom;
        this.matDoTrungBinh = lanTheoDoiTangTruong.matDoTrungBinh;
        this.khoiLuongTrungBinh = lanTheoDoiTangTruong.khoiLuongTrungBinh;
        this.tocDoSinhTruong = lanTheoDoiTangTruong.tocDoSinhTruong;
        this.tyLeSong = lanTheoDoiTangTruong.tyLeSong;
        this.sinhKhoi = lanTheoDoiTangTruong.sinhKhoi;
        this.ghiChu = lanTheoDoiTangTruong.ghiChu;
    }

}

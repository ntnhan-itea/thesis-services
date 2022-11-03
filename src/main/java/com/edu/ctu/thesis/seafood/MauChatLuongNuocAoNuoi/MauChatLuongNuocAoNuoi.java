package com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi.cacchiso.ChiSoDO;
import com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi.cacchiso.ChiSoNhietDo;
import com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi.cacchiso.ChiSoPH;
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mau_chat_luong_nuoc_ao_nuoi")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class MauChatLuongNuocAoNuoi extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate ngayKiemTra;

    @Column
    private Integer tuoiTom;

    @Column(name = "chi_so_nh3")
    private Float chiSoNh3;

    @Column(name = "chi_so_do_man")
    private Float chiSoDoMan;

    @Column(name = "chi_so_do_trong")
    private Float chiSoDoTrong;

    @Column(name = "chi_so_do_kiem")
    private Float chiSoDoKiem;

    @Column(name = "tinh_trang_tom")
    private String tinhTrangTom;

    @Column(name = "tinh_trang_nuoc")
    @Convert(converter = TinhTrangNuocConverter.class)
    private TinhTrangNuoc tinhTrangNuoc;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "chiSoS", column = @Column(name = "do_s")),
            @AttributeOverride(name = "chiSoC", column = @Column(name = "do_c")),
    })
    private ChiSoDO chiSoDO = new ChiSoDO();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "chiSoS", column = @Column(name = "nhiet_do_s")),
            @AttributeOverride(name = "chiSoC", column = @Column(name = "nhiet_do_c")),
    })
    private ChiSoNhietDo chiSoNhietDo = new ChiSoNhietDo();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "chiSoS", column = @Column(name = "ph_s")),
            @AttributeOverride(name = "chiSoC", column = @Column(name = "ph_c")),
    })
    private ChiSoPH chiSoPH = new ChiSoPH();

    @Embedded
    private XuLyMoiTruong xuLyMoiTruong = new XuLyMoiTruong();

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

    public void copy(MauChatLuongNuocAoNuoi entity) {
        this.ngayKiemTra = entity.ngayKiemTra;
        this.tuoiTom = entity.tuoiTom;
        this.chiSoNh3 = entity.chiSoNh3;
        this.chiSoDoMan = entity.chiSoDoMan;
        this.chiSoDoTrong = entity.chiSoDoTrong;
        this.chiSoDoKiem = entity.chiSoDoKiem;
        this.tinhTrangTom = entity.tinhTrangTom;

        this.chiSoDO = (entity.chiSoDO);
        this.chiSoNhietDo = (entity.chiSoNhietDo);
        this.chiSoPH = (entity.chiSoPH);

        this.xuLyMoiTruong = entity.xuLyMoiTruong;
        this.tinhTrangNuoc = entity.tinhTrangNuoc;
    }

}
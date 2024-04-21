package com.edu.ctu.thesis.seafood.lanthucanthuoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lan_thuc_an_thuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanThucAnThuoc extends Validity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "ma_so_lo_thuc_an")
    private Integer maSoLoThucAn;

    @Column()
    private Float lan1;

    @Column()
    private Float lan2;

    @Column()
    private Float lan3;

    @Column()
    private Float lan4;

    @Column()
    private Float lan5;

    @Column(name = "luong_thuc_an")
    private Float luongThucAn;

    @Column(name = "luong_thuc_an_tich_luy")
    private Float luongThucAnTichLuy;

    @Column(name = "loai_thuoc")
    private String loaiThuoc;

    @Column(name = "lieu_luong_thuoc")
    private Float lieuLuongThuoc;

    @Column(name = "thoi_gian_dao_thai_thuoc")
    private Integer thoiGianDaoThaiNuoc;

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

    public void copy(LanThucAnThuoc entity) {
        this.maSoLoThucAn = entity.maSoLoThucAn;
        this.lan1 = entity.lan1;
        this.lan2 = entity.lan2;
        this.lan3 = entity.lan3;
        this.lan4 = entity.lan4;
        this.lan5 = entity.lan5;
        this.luongThucAn = entity.luongThucAn;
        this.luongThucAnTichLuy = entity.luongThucAnTichLuy;
        this.loaiThuoc = entity.loaiThuoc;
        this.lieuLuongThuoc = entity.lieuLuongThuoc;
        this.thoiGianDaoThaiNuoc = entity.thoiGianDaoThaiNuoc;
        this.ghiChu = entity.ghiChu;
    }

    

}

package com.edu.ctu.thesis.seafood.thanhphancaitao;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoi;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "thanh_phan_cai_tao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ThanhPhanCaiTao extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "ten", nullable = false)
    @NotBlank(message = "Ten thanh phan khong duoc bo trong!")
    private String ten;

    @Column(name = "loai")
    private String loai;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ngay_xu_ly")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ngayXuLy;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "chuan_bi_ao_id", nullable = false)
    @JsonIgnore
    private ChuanBiAoNuoi chuanBiAoNuoi;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(message = "Account should not be null")
    private User user;

    public void copy(ThanhPhanCaiTao thanhPhanCaiTao) {
        this.ten = thanhPhanCaiTao.ten;
        this.loai = thanhPhanCaiTao.loai;
        this.soLuong = thanhPhanCaiTao.soLuong;
        this.ngayXuLy = thanhPhanCaiTao.ngayXuLy;
        this.ghiChu = thanhPhanCaiTao.ghiChu;
    }

}

package com.edu.ctu.thesis.seafood.ketquathuhoach;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "ket_qua_thu_hoach")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KetQuaThuHoach extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "dien_tich_ao")
    private Float dienTichAo;

    @Column(name = "luong_tha_giong")
    private Long luongThaGiong;

    @Column(name = "ngay_tha_giong")
    private LocalDate ngayThaGiong;

    @Column(name = "ngay_thu_hoach")
    private LocalDate ngayThuHoach;

    @Column(name = "tong_so_ngay_nuoi")
    private Long tongSoNgayNuoi;

    @Column(name = "luong_thuc_an")
    private Float luongThucAnToanBo;

    @Column(name = "so_luong_thu_hoach")
    private Float soLuongThuHoach;

    @Column(name = "he_so_FCR")
    private Float heSoFCR;

    @Column(name = "co_su_dung_hoa_chat")
    private Boolean coSuDungHoaChatTrongAoNuoi;

    @OneToOne
    @JoinColumn(name = "nhat_ky_id", nullable = false, unique = true)
    @JsonIgnore
    private NhatKy nhatKy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(KetQuaThuHoach ketQuaThuHoach) {
        this.dienTichAo = ketQuaThuHoach.dienTichAo;
        this.luongThaGiong = ketQuaThuHoach.luongThaGiong;
        this.ngayThaGiong = ketQuaThuHoach.ngayThaGiong;
        this.ngayThuHoach = ketQuaThuHoach.ngayThuHoach;
        this.tongSoNgayNuoi = ketQuaThuHoach.tongSoNgayNuoi;
        this.luongThucAnToanBo = ketQuaThuHoach.luongThucAnToanBo;
        this.soLuongThuHoach = ketQuaThuHoach.soLuongThuHoach;
        this.heSoFCR = ketQuaThuHoach.heSoFCR;
        this.coSuDungHoaChatTrongAoNuoi = ketQuaThuHoach.coSuDungHoaChatTrongAoNuoi;
    }

    

}

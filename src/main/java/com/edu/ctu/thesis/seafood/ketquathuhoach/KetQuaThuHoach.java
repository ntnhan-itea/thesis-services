package com.edu.ctu.thesis.seafood.ketquathuhoach;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.edu.ctu.thesis.seafood.sonhatkynuoitom.SoNhatKyNuoiTom;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
@Table(name = "ket_qua_thu_hoach")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KetQuaThuHoach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dien_tich_ao")
    private float dienTichAo;

    @Column(name = "luong_tha_giong")
    private Long luongThaGiong;

    @Column(name = "ngay_tha_giong")
    private LocalDate ngayThaGiong;

    @Column(name = "ngay_thu_hoach")
    private LocalDate ngayThuHoach;

    @Column(name = "tong_so_ngay_nuoi")
    private Long tongSoNgayNuoi;

    @Column(name = "luong_thuc_an")
    private float luongThucAnToanBo;

    @Column(name = "so_luong_thu_hoach")
    private float soLuongThuHoach;

    @Column(name = "he_so_FCR")
    private float heSoFCR;

    @Column(name = "co_su_dung_hoa_chat")
    private Boolean coSuDungHoaChatTrongAoNuoi;

    @ManyToOne
    @JsonIgnore
    private SoNhatKyNuoiTom soNhatKyNuoiTom;


}

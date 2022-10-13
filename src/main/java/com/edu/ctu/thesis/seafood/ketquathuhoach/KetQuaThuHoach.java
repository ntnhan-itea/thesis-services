package com.edu.ctu.thesis.seafood.ketquathuhoach;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.validity.Validity;

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
    @JoinColumn(name = "nhat_ky_id", nullable = false)
    private NhatKy nhatKy;


}

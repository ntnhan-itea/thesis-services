package com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mau_chat_luong_nuoc_ao_nuoi")
public class MauChatLuongNuocAoNuoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate ngayKiemTra;

    @Column
    private Integer tuoiTom;

    @Column(name = "chi_so_do_cung")
    private float chiSoDO;

    @Column(name = "chi_so_ph")
    private float chiSoPH;

    @Column(name = "nhiet_do")
    private float nhietDo;

    @Column(name = "chi_so_nh3")
    private float chiSoNh3;

    @Column(name = "chi_so_do_man")
    private float chiSoDoMan;

    @Column(name = "chi_so_do_trong")
    private float chiSoDoTrong;

    @Column(name = "chi_so_do_kiem")
    private float chiSoDoKiem;

    @Column(name = "tinh_trang_nuoc")
    private TinhTrangNuoc tinhTrangNuoc;

}
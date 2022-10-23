package com.edu.ctu.thesis.seafood.dieukienmoitruong;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DieuKienMoiTruong {

    @Column(name = "do_sau")
    private Float doSau;

    @Column(name = "do_trong")
    private Float doTrong;

    @Column(name = "do_man")
    private Float doMan;

    @Column(name = "chi_so_DO")
    private Float chiSoDO;

    @Column(name = "chi_so_PH")
    private Float chiSoPH;

    @Column(name = "nhiet_do_khong_khi")
    private Float nhietDoKhongKhi;

    @Column(name = "nhiet_do_nuoc")
    private Float nhietDoNuoc;

    public void copy(DieuKienMoiTruong dieuKienMoiTruong) {
        this.doSau = dieuKienMoiTruong.doSau;
        this.doTrong = dieuKienMoiTruong.doTrong;
        this.doMan = dieuKienMoiTruong.doMan;
        this.chiSoDO = dieuKienMoiTruong.chiSoDO;
        this.chiSoPH = dieuKienMoiTruong.chiSoPH;
        this.nhietDoKhongKhi = dieuKienMoiTruong.nhietDoKhongKhi;
        this.nhietDoNuoc = dieuKienMoiTruong.nhietDoNuoc;
    }

}

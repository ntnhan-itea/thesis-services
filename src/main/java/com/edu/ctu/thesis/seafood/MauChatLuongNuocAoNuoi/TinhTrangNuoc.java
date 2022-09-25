package com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi;

public enum TinhTrangNuoc {
    KHONG_XAC_DINH(-1) ,O_NHIEM(0), SACH_SE(1);

    private int value;

    public int getValue() {
        return this.value;
    }

    private TinhTrangNuoc(int value) {
        this.value = value;
    }
}

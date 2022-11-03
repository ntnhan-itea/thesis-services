package com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class XuLyMoiTruong {
    
    @Column(name = "loai")
    private String loai;

    @Column(name = "lieu_luong")
    private Float lieuLuong;
}

package com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

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

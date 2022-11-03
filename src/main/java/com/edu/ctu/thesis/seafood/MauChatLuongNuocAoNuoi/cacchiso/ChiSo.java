package com.edu.ctu.thesis.seafood.MauChatLuongNuocAoNuoi.cacchiso;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class ChiSo {

    private Float chiSoS;

    private Float chiSoC;

    public void copy(ChiSo entity) {
        this.chiSoC = entity.chiSoC;
        this.chiSoS = entity.chiSoS;
    }
}

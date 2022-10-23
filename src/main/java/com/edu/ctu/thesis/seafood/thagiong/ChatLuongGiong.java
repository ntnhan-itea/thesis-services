package com.edu.ctu.thesis.seafood.thagiong;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ChatLuongGiong {
    XAU(0), TRUNG_BINH(1), KHA(2), TOT(3);

    private int value;

    public int getValue(){
        return this.value;
    }
}

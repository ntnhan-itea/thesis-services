package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

public enum NenDay {
    DAT_THIT(0), DAT_SET_PHA_CAT(1), DAT_CAT(2), TRAI_BAC(3);

    private int value;

    private NenDay(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

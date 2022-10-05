package com.edu.ctu.thesis.seafood.user;

public enum Gender {

    UNKNOWN(0), MALE(10), FEMALE(20);

    private int value;

    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

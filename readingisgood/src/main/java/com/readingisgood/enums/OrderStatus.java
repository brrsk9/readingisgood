package com.readingisgood.enums;

public enum OrderStatus {

    RECIEVED(0),
    PREPARING(1),
    ON_THE_WAY(2),
    COMPLETED(3);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

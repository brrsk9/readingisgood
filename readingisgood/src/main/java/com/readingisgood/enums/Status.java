package com.readingisgood.enums;

public enum Status {

    INACTIVE(0),
    ACTIVE(1);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

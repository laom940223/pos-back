package com.laron.pos.spring.entities;

public enum OperationType {

    SALE("SALE"),
    BUY("BUY"),
    CASHOUT("CASHOUT");



    private final String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getUrl() {
        return name;
    }

}

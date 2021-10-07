package com.personal.atmSimulatorBackEnd.enums;

public enum CashAmount {
    FIVEHUNDRED(500),
    ONETHOUSAND(1000),
    TWOTHOUSAND(2000),
    FIVETHOUSAND(5000),
    TENTHOUSAND(10000);

    private int cashAmount;

    private CashAmount(int cashAmount){
        this.cashAmount = cashAmount;
    }

    public int getCashAmount() {
        return cashAmount;
    }
}

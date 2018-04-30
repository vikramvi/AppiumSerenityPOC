package com.serenity.appium.poc.utils;

public class Enums {

    public enum Fees {
        DEPOSIT("+DEPOSIT*"),
        WASLT("+WASLT*"),
        CRV("+CRV*");
        String text;

        Fees(String text) {
            this.text = text;
        }
        public String getLabel() {
            return text;
        }
    }
}


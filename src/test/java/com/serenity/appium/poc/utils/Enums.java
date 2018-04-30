package com.serenity.appium.poc.utils;

public class Enums {

    public enum Fees {
        DEPOSIT ("+DEPOSIT*", IosPlpDataParser.ProductType.BEER),
        WASLT   ("+WASLT*", IosPlpDataParser.ProductType.LIQUOR),
        CRV     ("+CRV*", IosPlpDataParser.ProductType.BEER);
        private String text;
        private IosPlpDataParser.ProductType productType;

        Fees(String text, IosPlpDataParser.ProductType productType) {
            this.text = text;
            this.productType = productType;
        }
        public String getLabel() {
            return text;
        }
        public IosPlpDataParser.ProductType getProductType() {
            return productType;
        }
    }
}


package com.serenity.appium.poc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IosPlpDataParser {

    private static String priceRegex = "\uE81D\\s\\$((0\\.|[1-9][0-9]{0,4}\\.)[0-9]{2})\\s?(\\+DEPOSIT\\*|\\+CRV\\*|\\+WASLT\\*)?\\s?";
    private static String titleRegex = "(([A-Z]+[^A-Za-z])+'?\\.?-?\\s?)+(,\\s[0-9]+)?\\s?";
    private static String liquorSizeRegex = "(50ML|100ML|200ML|375ML|750ML|1L|1.75L)\\s?";
    private static String wineSizeRegex = "(187ML|375ML|720ML|750ML|1L|1.5L|1.8L|3L)\\s?";
    private static String beerSizeRegex = "((1\\/4 KEG|1\\/2 KEG)|" +
            "((18|24|30)?(4PK|6PK|12PK|18PK)?-?(11OZ|12OZ|22OZ)\\s(BTLS|ALMBTLS|BTL))|" +
            "((9|24|30)?(4PK|6PK|8PK|12PK|15PK)?-?(11OZ|12OZ|16OZ|24OZ)\\s(CANS|CAN)))\\s?";
    private static String reviewCountRegex = "\\([0-9]+\\)?\\s?";
    private static String wineDataStreamRegex = priceRegex + titleRegex + wineSizeRegex + reviewCountRegex;
    private static String beerDataStreamRegex = priceRegex + titleRegex + beerSizeRegex + reviewCountRegex;
    private static String liquorDataStreamRegex = priceRegex + titleRegex + liquorSizeRegex + reviewCountRegex;

    public enum ProductType {
        BEER (beerSizeRegex, beerDataStreamRegex),
        WINE (wineSizeRegex, wineDataStreamRegex),
        LIQUOR(liquorSizeRegex, liquorDataStreamRegex);
        String fullDataRegEx;
        String sizeRegEx;
        ProductType(String sizeRegEx, String fullDataRegEx) {
            this.fullDataRegEx = fullDataRegEx;
            this.sizeRegEx = sizeRegEx;
        }
        public String getFullDataRegEx() {
            return this.fullDataRegEx;
        }
        public String getSizeRegEx() {
            return this.sizeRegEx;
        }
    };

    public static boolean isFullProductDatasetFound(String productDataStream, ProductType productType) {
        Pattern pattern = Pattern.compile(productType.getFullDataRegEx());
        Matcher matcher = pattern.matcher(productDataStream);
        boolean found = false;
        if (matcher.find()) {
            System.out.println("found (all) --> " + matcher.group());
            System.out.println("found --> " + matcher.group(1));
            System.out.println("found --> " + matcher.group(2));
            System.out.println("found --> " + matcher.group(3));
            System.out.println("found --> " + matcher.group(4));
            System.out.println("found --> " + matcher.group(5));
            System.out.println("found --> " + matcher.group(6));
            System.out.println("found --> " + matcher.group(7));
            System.out.println("found --> " + matcher.group(8));
            System.out.println("found --> " + matcher.group(9));
            System.out.println("found --> " + matcher.group(10));
            System.out.println("found --> " + matcher.group(11));
            System.out.println("found --> " + matcher.group(12));
            System.out.println("found --> " + matcher.group(13));
            System.out.println("found --> " + matcher.group(14));
            System.out.println("found --> " + matcher.group(15));
            int index = matcher.start();
            if (index == 0) {
                found = true;
            }
        }
        return found;
    }

    private static List<String> parseRowIntoDataPerColumn(String rowStream, ProductType productType) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(productType.getFullDataRegEx());
        Matcher matcher = pattern.matcher(rowStream);
        while (matcher.find()) {
            result.add(matcher.group(0));
        }
        if (result.size() != 2) {
            throw new IllegalStateException("Row data (" +rowStream+ ") could not be parsed into data per column!");
        }
        return result;
    }

    public enum Column {
        LEFT    (0),
        RIGHT   (1);
        private int rowIndex;
        Column(int rowIndex) {
            this.rowIndex = rowIndex;
        }
        public int getRowIndex() {
            return rowIndex;
        }
    };

    public static String getDataFromRow(String rowStream, ProductType productType, Column column) {
        String result = parseRowIntoDataPerColumn(rowStream, productType).get(column.getRowIndex());
        return result;
    }

    public static String getProductAttributeFromRow(String rowStream, ProductType productType, Column column, ProductAttribute attribute) {
        String productDataStream = getDataFromRow(rowStream, productType, column);
        String result = attribute.getData(productDataStream);
        return result;
    }

    public enum ProductAttribute {
        PRICE       (priceRegex, 1),
        FEE         (priceRegex, 3),
        TITLE       (titleRegex, 1),
        LIQUOR_SIZE (liquorSizeRegex, 1),
        WINE_SIZE   (wineSizeRegex, 1),
        BEER_SIZE   (beerSizeRegex,1);
        private String regEx;
        private int groupNumber;
        ProductAttribute(String regEx, int groupNumber) {
            this.regEx = regEx;
            this.groupNumber = groupNumber;
        }
        public String getData(String productDataStream) {
            String result = noResultFound;
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(productDataStream);
            if (matcher.find()) {
                result = matcher.group(groupNumber);
            } else {
                throw new IllegalStateException("No match for " +this.toString()+ " in data stream!");
            }
            return result;
        }
    }

    public static final String noResultFound = "NOT FOUND!";
}

package com.serenity.appium.poc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IosPdpDataParser {

    private static String titleRegex = "(([A-Z]+[^A-Za-z])+'?\\.?-?\\s?)+(,\\s[0-9]+)?\\s?";
    private static String categoriesRegex = "([A-Z][a-z]+-?\\s?\\/?\\s?)+";
    private static String reviewCountRegex = "\\([0-9]+\\)?\\s?";
    private static String priceRegex = "\\$((0\\.|[1-9][0-9]{0,4}\\.)[0-9]{2})\\s?(\\+DEPOSIT\\*|\\+CRV\\*|\\+WASLT\\*)?\\s?";
    private static String liquorSizeRegex = "(50ML|200ML|375ML|750ML|1L|1.75L)\\s\uE313\\s?";
    private static String wineSizeRegex = "(187ML|375ML|720ML|750ML|1L|1.5L|1.8L|3L)\\s\uE313\\s?";
    private static String beerSizeRegex = "(((KEG)\\s-\\s(1\\/4 KEG|1\\/2 KEG)\\s-\\s(SINGLE))|" +
            "((BOTTLE)\\s-\\s(12OZ|24OZ)\\s-\\s(SINGLE|PACK(\\(6\\)|\\(18\\))|CASE(\\(18\\)|\\(24\\))))" +
            "((CAN)\\s-\\s(12OZ|16OZ|24OZ)\\s-\\s(SINGLE|PACK(\\(4\\)|\\(12\\))|CASE(\\(18\\)|\\(24\\)))))\\s?";
    private static String quantityRegex = "QTY:\\s[1-9][0-9]{0,5}\\s\uE313\\s?";
    private static String availabilityRegex = "(PRODUCT AVAILABILITY )(\uF3FF )?(Pick up in store )(Limited quantity|" +
            "Ready Today)(\\s(\\w+\\s)+)CHANGE(( \uE82B Delivery )((\\w+\\s?\\.?)+))?( CONFIRM)?\\s?";
    private static String detailsRegex = "((DETAILS )(([a-z]+\\-?\\s?)+)\"(([\\w\\-\\s\\\",\\.'\\%]?)+)\")\\s?";
    private static String skuRegex = "(SKU: )(\\d+(\\-\\d)?)\\s?";
    private static String reviewsRegex = "((REVIEWS )(\\d\\.\\d)\\s(\\d+ reviews)\\s(WRITE A REVIEW SEE ALL REVIEWS))?";
    private static String wineDataStreamRegex = titleRegex + categoriesRegex + reviewCountRegex + priceRegex + wineSizeRegex
                                            + quantityRegex + availabilityRegex + detailsRegex + skuRegex + reviewsRegex;
    private static String beerDataStreamRegex = titleRegex + categoriesRegex + reviewCountRegex + priceRegex + beerSizeRegex
                                            + quantityRegex + availabilityRegex + detailsRegex + skuRegex + reviewsRegex;
    private static String liquorDataStreamRegex = titleRegex + categoriesRegex + reviewCountRegex + priceRegex + liquorSizeRegex
                                            + quantityRegex + availabilityRegex + detailsRegex + skuRegex + reviewsRegex;

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

//    public static String stripTitle(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, titleRegex);
//    }
//    public static String stripAddress(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, categoriesRegex);
//    }
//    public static String stripReviewCount(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, reviewCountRegex);
//    }
//    public static String stripPrice(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, priceRegex);
//    }
//
//    public static String stripWineSize(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, wineSizeRegex);
//    }
//
//    public static String stripQuantity(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, quantityRegex);
//    }
//
//    public static String stripAvailability(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, availabilityRegex);
//    }
//
//    public static String stripDetails(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, detailsRegex);
//    }
//
//    public static String stripSku(String productDataStream) {
//        return Utils.stripFrontRegex(productDataStream, skuRegex);
//    }
//
    public enum ProductData {
        TITLE       (titleRegex, 1),
        CATEGORIES  (categoriesRegex, 1),
        PRICE       (priceRegex, 1),
        FEE         (priceRegex, 3),
        LIQUOR_SIZE (liquorSizeRegex, 1),
        WINE_SIZE   (wineSizeRegex, 1),
        BEER_SIZE   (beerSizeRegex,1),
        AVAILABILITY(availabilityRegex, 1),
        SKU         (skuRegex, 1);
        private String regEx;
        private int groupNumber;
        ProductData(String regEx, int groupNumber) {
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
//    public static String getTitle(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(titleRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for product title in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getCategories(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(categoriesRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for categories in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getPrice(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(priceRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for price in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getFee(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(priceRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find() && !matcher.group(3).isEmpty()) {
//            result = matcher.group(3);
//        } else {
//            throw new IllegalStateException("No match for fee in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getBeerSize(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(beerSizeRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for beer size in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getWineSize(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(wineSizeRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for wine size in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getQuantity(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(quantityRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for quantity in " + productDataStream + "!");
//        }
//        return result;
//    }
//    public static String getAvailability(String productDataStream) {
//        String result = noResultFound;
//        Pattern pattern = Pattern.compile(availabilityRegex);
//        Matcher matcher = pattern.matcher(productDataStream);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for availability in " + productDataStream + "!");
//        }
//        return result;
//    }
}

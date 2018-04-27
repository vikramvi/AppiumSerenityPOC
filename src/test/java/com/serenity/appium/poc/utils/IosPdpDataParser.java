package com.serenity.appium.poc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IosPdpDataParser {

    private static String titleRegex = "(([A-Z]+[^A-Za-z])+'?\\.?-?\\s?)+(,\\s[0-9]+)?\\s?";
    private static String categoriesRegex = "([A-Z][a-z]+-?\\s?\\/?\\s?)+";
    private static String reviewCountRegex = "\\([0-9]+\\)?\\s?";
    private static String priceRegex = "\\$(0\\.|[1-9][0-9]{0,4}\\.)[0-9]{2}\\s?(\\+DEPOSIT\\*|\\+CRV\\*|\\+WASLT\\*)?\\s?";
    private static String wineSizeRegex = "187ML|375ML|720ML|750ML|1L|1.5L|1.8L|3L)\\s\uE313\\s?";
    private static String beerSizeRegex = "(((KEG)\\s-\\s(1\\/4 KEG|1\\/2 KEG)\\s-\\s(SINGLE))|" +
            "((BOTTLE)\\s-\\s(12OZ|24OZ)\\s-\\s(SINGLE|PACK(\\(6\\)|\\(18\\))|CASE(\\(18\\)|\\(24\\))))" +
            "((CAN)\\s-\\s(12OZ|16OZ|24OZ)\\s-\\s(SINGLE|PACK(\\(4\\)|\\(12\\))|CASE(\\(18\\)|\\(24\\)))))\\s?";
    private static String quantityRegex = "QTY:\\s[1-9][0-9]{0,5}\\s\uE313\\s?";
    private static String availabilityRegex = "(PRODUCT AVAILABILITY )(\uF3FF )?(Pick up in store )(Limited quantity|" +
            "Ready Today)(\\s(\\w+\\s)+)CHANGE(( \uE82B Delivery )((\\w+\\s?\\.?)+))?( CONFIRM)?\\s?";
    private static String detailsRegex = "((DETAILS )(([a-z]+\\-?\\s?)+)\"(([\\w\\-\\s\\\",\\.'\\%]?)+)\")\\s?";
    private static String skuRegex = "(SKU: )(\\d+(\\-\\d)?)\\s?";
    private static String reviewsRegex = "((REVIEWS )(\\d\\.\\d)\\s(\\d+ reviews)\\s(WRITE A REVIEW SEE ALL REVIEWS))?";
    private static String dataStreamRegex = titleRegex + categoriesRegex + reviewCountRegex + priceRegex + wineSizeRegex
                                            + quantityRegex + availabilityRegex + detailsRegex + skuRegex + reviewsRegex;

    public static boolean isFullStoreDataFound(String productDataStream) {
        Pattern pattern = Pattern.compile(dataStreamRegex);
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

    public static String stripTitle(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, titleRegex);
    }
    public static String stripAddress(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, categoriesRegex);
    }
    public static String stripReviewCount(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, reviewCountRegex);
    }
    public static String stripPrice(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, priceRegex);
    }

    public static String stripWineSize(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, wineSizeRegex);
    }

    public static String stripQuantity(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, quantityRegex);
    }

    public static String stripAvailability(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, availabilityRegex);
    }

    public static String stripDetails(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, detailsRegex);
    }

    public static String stripSku(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, skuRegex);
    }

    public static final String noResultFound = "NOT FOUND!";
    public static String getTitle(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(titleRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for product title in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getCategories(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(categoriesRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for categories in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getPrice(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(priceRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for price in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getWineSize(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(wineSizeRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for wine size in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getQuantity(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(quantityRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for quantity in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getAvailability(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(availabilityRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for availability in " + storeDataStream + "!");
        }
        return result;
    }
}

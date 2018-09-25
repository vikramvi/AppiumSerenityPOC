package com.serenity.appium.poc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoreDataParser {

    private static String titleRegex = "(([0-9]+\\s)?([A-Z]+'?[A-Z]+?\\.?-?\\s)+(\\([A-Z]+\\)\\s)?)";
    private static String addressRegex = "(([0-9]+\\s)?([A-Z][a-z]+\\.?-?\\s)+([0-9]+)?)";
    private static String cityRegex = "(([A-Z][a-z]+\\.?\\s?)+),";
    private static String stateRegex = "([A-Z]{2})";
    private static String zipRegex = "([0-9]{5}(-[0-9]{4})?)";
    private static String cityStateZipRegex = "(" + cityRegex + "\\s" + stateRegex + "\\s" + zipRegex + ")";
    private static String phoneNumberRegex = "(\\([0-9]{3}\\)\\s[0-9]{3}\\-[0-9]{4})";
    private static String daysRegex = "((Mon|Tues|Wed|Thu|Fri|Sat|Sun)\\s)?";
    public static String openCloseRegex = "((Opens|Open)\\s" +daysRegex+ "(at|Until)\\s(1[012]|[1-9])\\s(AM|PM))";
    private static String fullStoreData = titleRegex+addressRegex+"?\\s?("+cityRegex+"\\s"+stateRegex
            +"\\s"+zipRegex+")"+"\\s"+openCloseRegex;

    public static boolean isFullStoreDataFound(String storeDataStream) {
        Pattern pattern = Pattern.compile(fullStoreData);
        Matcher matcher = pattern.matcher(storeDataStream);
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
        return Utils.stripFrontRegex(storeDataStream, addressRegex);
    }
    public static String stripCityStateZip(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, cityStateZipRegex);
    }
    public static String stripPhoneNumber(String storeDataStream) {
        return Utils.stripFrontRegex(storeDataStream, phoneNumberRegex);
    }

    public static final String noResultFound = "NOT FOUND!";
    public static String getTitle(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(titleRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for title in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getLocation(String storeDataStream) {
        String result = Utils.stripFrontRegex(storeDataStream, titleRegex);
        result = Utils.stripAnyRegex(result, openCloseRegex);
//        return Utils.trimFinalSpace(result);
        return result.trim();
    }
    public static String getAddress(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(4);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for address in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getCityStateZip(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(cityStateZipRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(8);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for city/state/zip in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getCity(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(cityRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(9);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for city in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getState(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(stateRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(11);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for state in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getZip(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(zipRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(12);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for zip in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getPhoneNumber(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for phone number in " + storeDataStream + "!");
        }
        return result;
    }
    public static String getOpenCloseHour(String storeDataStream) {
        String result = noResultFound;
        Pattern pattern = Pattern.compile(openCloseRegex);
        Matcher matcher = pattern.matcher(storeDataStream);
        if (matcher.find()) {
//            result = matcher.group(14);
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for open/close hour in " + storeDataStream + "!");
        }
        return result;
    }
}

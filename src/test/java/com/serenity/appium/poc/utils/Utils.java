package com.serenity.appium.poc.utils;

import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class Utils {
    public static String getPlatform(AppiumDriver driver) {
        return String.valueOf(driver.getCapabilities().getCapability("platformName"));
    }

    public static boolean isIosPlatform(AppiumDriver driver) {
        String platform = getPlatform(driver);
        return platform.equalsIgnoreCase("ios");
    }

    public static boolean isAndroidPlatform(AppiumDriver driver) {
        return getPlatform(driver).equalsIgnoreCase("android");
    }

    public static String acceptAlert(AppiumDriver driver) {
        String message = null;
        if (isIosPlatform(driver)) {
            Alert popup = driver.switchTo().alert();
            message = popup.getText();
            popup.accept();
        } else if (Utils.isAndroidPlatform(driver)) {
            message = driver.findElement(By.id("android:id/message")).getText();
            //TODO: need to access OK button on Android
        } else throw new IllegalStateException("Platform not iOS or Android!");
        return message;
    }

    public static String stripFrontRegex(String stream, String regex) {
        stream = stream.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stream);
        String result = "NOT IN POSITION 0 !";
        if (matcher.find()) {
            if (matcher.start() == 0) {
                String matched = matcher.group();
                result = stream.replaceFirst(matched, "");
            } else {
                throw new IllegalStateException("Cannot strip expression when it is not in position 0!");
            }
        }
        return result;
    }

    public static String stripAnyRegex(String stream, String regex) {
        stream = stream.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stream);
        String result = "NOT FOUND!";
        if (matcher.find()) {
            if (matcher.start() >= 0) {
                String matched = matcher.group();
                result = stream.replace(matched, "");
            } else {
                throw new IllegalStateException("Cannot strip expression when it is not found!");
            }
        }
        return result;
    }

    public static String trimFinalSpace(String string) {
        if (string != null && string.length() > 0 && string.charAt(string.length() - 1) == ' ') {
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }

    public static boolean isVisible(WebDriver driver, By reference, int seconds) {
        try{
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(reference));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean isVisible(WebDriver driver, WebElement element, int seconds) {
        try{
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean isClickable(WebDriver driver, WebElement element, int seconds) {
        try{
            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    public static boolean isLastInstanceVisible(WebDriver driver, String xpath) {
        int lastInstance = driver.findElements(By.xpath(xpath)).size();
        String suffix = String.format("[%d]", lastInstance);
        boolean result = ((lastInstance > 0) &&
                (driver.findElement(By.xpath(xpath + suffix)).isDisplayed()));
        return result;
    }

    public static boolean tryClicking(String xpath) {
        WebElement element = getDriver().findElement(By.xpath(xpath));
        return tryClicking(element);
    }

    public static boolean tryClicking(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryClicking(WebDriver driver, By byReference) {
        try {
            driver.findElement(byReference).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryClicking(WebDriver driver, WebElement element) {
        try {
            boolean visible = isVisible(driver, element, 5);
            if (visible) {
                element.click();
            }
            return visible;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryClickingAllow() {
        try {
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("action", "accept");
            tapObject.put("label", "Allow");

            js.executeScript("mobile:alert", tapObject);
            return true;
        } catch (Exception e) {
            System.out.println("Caught exception trying to click Allow!");
            return false;
        }
    }

    public static String getTextFromByHandle(WebDriver driver, By by, boolean scrollDown) {
        if (scrollDown) {
            Scrolling.scrollDown();
        }
        String text = driver.findElement(by).getText();
        return text;
    }

    public static String getAllAndroidGridData(String gridElementXpath) {
        String result = "";
        String gridElementXpathPattern = gridElementXpath + "[%d]";
        int totalGridItems = getDriver().findElements(MobileBy.xpath(gridElementXpath)).size();
        for (int i = 1; i <= (totalGridItems); i++) {
            String xpath = String.format(gridElementXpathPattern, i);
            boolean itemExists = (getDriver().findElements(MobileBy.xpath(xpath)).size() > 0);
            if (itemExists) {
                String text = getDriver().findElement(MobileBy.xpath(xpath)).getText() + " ";
                result = result + text;
            }
        }
        return result;
    }

    public static void waitFor(int milleseconds) {
        try {
            Thread.sleep(milleseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public enum Axis {X, Y};
    public static int getScreenDimension(Axis axis) {
        Dimension dim = getDriver().manage().window().getSize();
        int result = 0;
        if (axis.equals(Axis.X)) {
            result = dim.getWidth();
        } else if (axis.equals(Axis.Y)) {
            result = dim.getHeight();
        }
        return result;
    }

    public static boolean clickIosUpdateButton() {
        try {
            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_UPDATE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean clickIosReturnButton() {
        try {
            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_RETURN);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPageTitleCorrectAfterPolling(WebElement element, String expectedTitle) {
        boolean found = false;
        int i = 0;
        while (i<12 && !found) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String actual = element.getText();
            found = actual.equals(expectedTitle);
            i++;
        }
        return found;
    }

    private static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min + 1)) + min;
    }
    public static String getRandomLoginId() {
        int randomInt = getRandomInteger(1, 100);
        return String.format("appTest%d@yopmail.com", randomInt);
    }
    public static String getRandomLoginIdWithCertificate() {
        int randomInt = getRandomInteger(2, 10);
        return String.format("jphTest%d@yopmail.com", randomInt);
    }
    private static int getRandomInteger(int max) {
        return getRandomInteger(0, max);
    }

    private static String getRandomName(String[] names) {
        int size = names.length;
        int index = getRandomInteger(size-1);
        return names[index];
    }

    private static String[] lastNames = {
        "Morgan", "Cooke", "Deleon", "Frazier", "Sampson", "Hennessey", "Roetsson", "Simone", "Mintz",
            "Blankenship", "Kennedy", "Santana", "Brady", "Griffith", "Harding", "Osborne", "Benitez",
            "Curtis", "Randolph", "Wilkinson", "Smith", "Stone", "Ward", "Crawford", "Crow", "Morrison",
            "Sanders", "Baelish", "Bolton", "Greyjoy", "Lannister", "Moro", "Sand", "Naharis", "Seaworth",
            "Stark", "Targaryen", "Tarly", "Tyrell", "Snow", "Carter", "Botwin", "Pereira", "Voloshina",
            "Miclot"
};
public static String getRandomLastName() {
//        return getHyphenatedName(lastNames);
        return getRandomName(lastNames);
        }
private static String[] firstNames = {
        "Joan", "Lindsey", "Erik", "Toby", "Dale", "Kristina", "Sixcia", "Dawn", "Jami", "Ali",
        "Tim", "Mike", "Alex", "Veronica", "Dan", "Lawrence", "Helen", "Beth", "Ann", "Hans-Petter",
        "Ken", "Ben", "Dom", "Jason", "Gin", "Cait", "Steve", "Reagan", "Neve", "Hanni", "Anne",
        "Beth", "John", "Mac", "Jamie", "Jennifer", "Trey", "Jeff", "Dan", "Dawn", "Zoe", "Ryan",
        "Skylar", "Holden", "Bruce", "Van", "Bernie", "Olenna", "Varys", "Arya", "Bran", "Sansa",
        "Jon", "Tyene", "Davos", "Ramsay", "Samwell", "Margaery", "Daario", "Ellaria", "Nym", "Mick",
        "Obara", "Melisandre", "Missandei", "Jorah", "Khal", "Jaquen", "Cersei", "Jaime", "Tyrion", "Jp",
        "Bronn", "Gilly", "Euron", "Theon", "Katie", "Jillian", "Keith", "Nick", "Rigel", "Albert",
        "Judy", "Arthur", "Kristin", "Daria", "Amanda", "Simone", "Jeffrey", "Elizabeth", "Paul", "Ann",
        "Jim", "Ken", "Diane", "Cathy", "Nicole", "Liz", "Caroline", "Richard", "Rachael", "Kevin",
        "Ali", "Louise", "Patrick", "Denise", "Madelaine", "Douglas", "Raymond", "James", "Daniel", "Alexander"
        };
public static String getRandomFirstName() {
        return getRandomName(firstNames); // getHyphenatedName(firstNames);
        }
////public static String getRandomFullName() {
////        return getRandomFirstName() + " " + getRandomLastName();
////        }
//private static String getHyphenatedName(String[] list) {
//        String firstHalf = getRandomName(list);
//        String lastHalf = getRandomName(list);
//        String hyphenatedName = firstHalf + "-" + lastHalf;
//        return hyphenatedName;
//        }

    public static String getRandomEmailAddress() {
        String random = RandomStringUtils.randomAlphabetic(10);
        return "jphtest" + random + "@yopmail.com";
    }

    public static void setPlatform() {
        //String platform = System.getProperty("testEnvironment");

        //ACHTUNG - Hard Coded To Be Removed
        String platform = "Android";

        switch (platform) {
            case ("Android"):
                MobilePageObject.setAndroid(true);
                MobilePageObject.setIOS(false);
                break;
            case ("iOS"):
                MobilePageObject.setIOS(true);
                MobilePageObject.setAndroid(false);
                break;
            default:
                try {
                    throw new IllegalAccessException("No match for plaform!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

}

package com.serenity.appium.poc.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.base.Predicate;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//https://github.com/serenity-bdd/serenity-core/issues/660
//http://appium.github.io/java-client/io/appium/java_client/ios/IOSDriver.html#method.summary
//https://github.com/serenity-bdd/serenity-core/issues/936

public class MobilePageObject extends PageObject {
    //https://github.com/ricardorlg/serenityappiumbug/issues/1
    //WhenUsingCustomLogicForElementsInit.java, PageObject.java
    public MobilePageObject(final WebDriver driver) {
        super(driver, new Predicate<PageObject>() {
            @Override
            public boolean apply(PageObject page) {

                PageFactory
                        .initElements(new AppiumFieldDecorator(((WebDriverFacade) page.getDriver()).getProxiedDriver(),
                                page.getImplicitWaitTimeout().in(TimeUnit.SECONDS), TimeUnit.SECONDS), page);
                return true;
            }
        });
    }

    protected static final String noResultsFound = "NOT_FOUND!";

    //NOTE: the following can only be used if the platform is passed in as a MVN argument (e.g. clean verify test -e -DtestEnvironment=iOS -Dmaven.surefire.debug)
    private static boolean isIOS;
    private static boolean isAndroid;

    public static void setAndroid(boolean android) {
        isAndroid = android;
    }

    public static boolean isAndroid() {
        return isAndroid;
    }

    public static void setIOS(boolean iOS) {
        isIOS = iOS;
    }

    public static boolean isIOS() {
        return isIOS;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(MobilePageObject.class);
}

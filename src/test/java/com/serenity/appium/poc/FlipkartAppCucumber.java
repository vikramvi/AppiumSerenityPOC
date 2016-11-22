package com.serenity.appium.poc;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static com.serenity.appium.poc.AppiumServerVikram.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerVikram.stopAppiumServer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/java/resources/features/invalid_login.feature")
public class FlipkartAppCucumber {

    @BeforeClass
    public static void startAppium() {
        startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        stopAppiumServer();
    }
}

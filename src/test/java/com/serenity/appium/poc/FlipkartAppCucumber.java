package com.serenity.appium.poc;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static com.serenity.appium.poc.AppiumServerActions.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerActions.stopAppiumServer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/java/resources/features/invalid_login.feature" , plugin = {"json:target/cucumber_json/cucumber.json"} )
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

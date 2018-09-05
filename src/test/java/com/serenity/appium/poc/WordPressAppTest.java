package com.serenity.appium.poc;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import net.thucydides.core.webdriver.WebDriverFacade;
import org.junit.*;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.serenity.*;

import java.time.Duration;

import static com.serenity.appium.poc.AppiumServerController.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerController.stopAppiumServer;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

@RunWith(SerenityRunner.class)
public class WordPressAppTest {
    
    @Managed(uniqueSession = false)
    public WebDriver webdriver;
    
    @Steps
    public WordPressLoginSteps userSteps;

    @Steps
    public WordPressSignUpSteps signUpSteps;
     
    @BeforeClass
    public static void startAppium() {
        startAppiumServer();
    }

    @AfterClass
    public static void stopAppiumAndRemoveApp() {
        //((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).resetApp();
        ((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).removeApp("org.wordpress.android");
        stopAppiumServer();
    }

    @Before
    public void appBaseState(){
        ((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).activateApp("org.wordpress.android");
        //((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).launchApp();
    }

    @After
    public void putAppInBackgroundOrTerminate(){
        //((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).closeApp();
        ((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).terminateApp("org.wordpress.android");
        //((AppiumDriver)((WebDriverFacade) getDriver()).getProxiedDriver()).runAppInBackground(Duration.ofSeconds(1));
    }
     
    @Test
    public void verifyInvalidLogin(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData();
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }

    @Test
    public void verifyInvalidSignUp(){
        try {
            signUpSteps.clickCreateAWordPressSiteButton();
            signUpSteps.enterInvalidDataInSignUpPage();
            signUpSteps.clickCreateAccountButton();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


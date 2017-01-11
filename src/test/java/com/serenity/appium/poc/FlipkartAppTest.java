package com.serenity.appium.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.cucumber_related.FlipkartLoginSteps;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.serenity.appium.poc.AppiumServerVikram.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerVikram.stopAppiumServer;

@RunWith(SerenityRunner.class)
public class FlipkartAppTest{
    
    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public FlipkartLoginSteps userSteps;
     
    @BeforeClass
    public static void startAppium() {
        //startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        //stopAppiumServer();
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
    public void verifyYahooInvalid(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData2();
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
      
}


//Notes
//1. appium.platformName = Android
//appium.platformVersion = 4.4.2
//appium.deviceName  = Nexus_4_1
//appium.app =  /Users/vikram-anna/Documents/Noa/Workspace/Mobile-Automation/Android-Automation/serenityAppiumFlipkart/com.microsoft.office.word_16.0.7668.4775-2001376635_minAPI19(x86)(nodpi)_APKdot.com.apk
//appium.hub = http://localhost:4723/wd/hub


//2. mvn clean verify -Dappium.hub=http://127.0.0.1:4444/wd/hub -Dwebdriver.driver=appium -Dappium.platformName=Android -Dappium.app=Flipkart.3.0.apk -Dappium.deviceName=dummy

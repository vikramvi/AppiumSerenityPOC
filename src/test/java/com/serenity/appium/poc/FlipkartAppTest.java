package com.serenity.appium.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.FlipkartLoginSteps;


@RunWith(SerenityRunner.class)
public class FlipkartAppTest{
    
    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public FlipkartLoginSteps userSteps;
            
    @Test
    public void verifyInvalidLogin(){
	userSteps.loginPageInvalidDataInput();
    }
      
}


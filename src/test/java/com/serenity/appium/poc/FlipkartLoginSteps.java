package com.serenity.appium.poc;

import net.thucydides.core.annotations.Step;

public class FlipkartLoginSteps{
      private static FlipkartLoginPage loginPage;
       
     @Step
     public void loginPageInvalidDataInput(){
	 loginPage.gotoLoginPage();
     }
     
}
package com.serenity.appium.poc.cucumber_related;

import com.serenity.appium.poc.FlipkartLoginPage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class FlipkartLoginSteps extends ScenarioSteps{
      private static FlipkartLoginPage loginPage;
       
     @Step
     public void loginPageInvalidDataInput(){
	 loginPage.gotoLoginPage();
     }
     
     @Step
     public void enterLoginData(){
	 loginPage.enterInvalidCredentials();
     }
     
     @Step
     public void enterLoginData2(){
	 loginPage.enterInvalidCredentials2();
     }
     
     @Step
     public void checkErrorMessage(){
	 assertThat( loginPage.isErrorMessageShown() ).isTrue();
     }
     
}
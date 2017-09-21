package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.WordPressLoginPage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPressLoginSteps extends ScenarioSteps{
      WordPressLoginPage loginPage;
       
     @Step
     public void loginPageInvalidDataInput(){
	 //loginPage.gotoLoginPage();
         loginPage.gotoWPLoginPage();
     }
     
     @Step
     public void enterLoginData(){
	  //loginPage.enterInvalidCredentials();
          loginPage.enterInvalidEmailIdWPLoginPage();
     }
     
     @Step
     public void checkErrorMessage(){
	   //assertThat( loginPage.isErrorMessageShown() ).isTrue();
          assertThat(loginPage.isErrorMessageShownWPLoginPage()).isTrue();
     }
     
}
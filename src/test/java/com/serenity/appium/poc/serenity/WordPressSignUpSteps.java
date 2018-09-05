package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.WordPressSignUpPage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPressSignUpSteps extends ScenarioSteps{
    WordPressSignUpPage signUpPage;

    @Step
    public void clickCreateAWordPressSiteButton(){
        signUpPage.gotoWPSignUpPage();
    }

    @Step
    public void enterInvalidDataInSignUpPage(){
        signUpPage.enterSignUpScreenDetails();
    }

    @Step
    public void clickCreateAccountButton(){
        signUpPage.clickSignUpButton();
    }

}

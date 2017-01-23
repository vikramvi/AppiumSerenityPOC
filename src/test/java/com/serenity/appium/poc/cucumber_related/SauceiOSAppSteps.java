package com.serenity.appium.poc.cucumber_related;

import com.serenity.appium.poc.ios.SauceiOSAppPage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class SauceiOSAppSteps extends ScenarioSteps{
    private static SauceiOSAppPage iOSPage;
    
    @Step
    public void enterEmailID(){
	iOSPage.inputEmail();
    }
    
}

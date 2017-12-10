package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.WineAppPageObject;
import com.serenity.appium.poc.pages.WordPressLoginPage;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class WineAppSteps extends ScenarioSteps {

    WineAppPageObject wineAppPageObject;
    String winName = "Sweet Red Wine";

    @Step
    public void completeSplashScreenChecks(){
        assertThat( wineAppPageObject.completeSpalshScreenActions() ).isTrue();
    }

    @Step
    public void doWineSearch(){
        assertThat( wineAppPageObject.performSearchActionWithValidWineName(winName) ).isTrue();
    }

    @Step
    public void selectWineNameFromSearchResultView(){
      assertThat( wineAppPageObject.selectWineNameFromSearchResults(winName) ).isTrue();
      //wineAppPageObject.addAllItemsShownToUser_OnScreenForParticularSearchTermToCart();
        wineAppPageObject.performSwipe();
    }

    @Step
    public void performSortAction(){
      assertThat( wineAppPageObject.performSortActionByHighPrice() ).isTrue();
    }
}

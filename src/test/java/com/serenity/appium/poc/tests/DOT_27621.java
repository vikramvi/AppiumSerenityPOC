package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_27621 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_27621_verifyAdditionOfItemToMultipleLists() {
        appSteps.completeQuickOnboarding();
        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoHomeTab();
        appSteps.verifyMaxListsDisplayedUnderMyListsSection();
        appSteps.verifyFavoriteItemCanBeAddedToMultipleLists();
    }
}

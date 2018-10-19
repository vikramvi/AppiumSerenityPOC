package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_27625 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_27625_verifyLoyaltyBasedOnUsersPreferredStore() {
        appSteps.completeQuickOnboarding();
//        String emailId = "jphtest@yopmail.com";
        appSteps.performLoginFromHomepage(Utils.getRandomLoginId());

        appSteps.changePreferredStore("NY", "River Edge");
        appSteps.gotoHomeTab();
        appSteps.isMoreRewardsSectionShouldDisplay(false);


        appSteps.changePreferredStore("Orlando", "Orlando (Colonial Plaza)");
        appSteps.gotoHomeTab();
        appSteps.isMoreRewardsSectionShouldDisplay(true);
    }
}

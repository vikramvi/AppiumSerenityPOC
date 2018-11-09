package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.StoreDetails;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_27645 extends WineAppTest{

    //@Test  WIP can't interact with any of the elements on "&More Rewards" screen. Appium simply hangs, no error log in server shown.
    //@Category({StoreDetails.class})
    public void DOT_27645_verifyPromptGuestToSignInORJoinNow_AndMoreRewardsScreen(){
        appSteps.completeQuickOnboarding();
        appSteps.verifyUserIsOnHomeTab();
        appSteps.verifyAndMoreRewardsFlowFromMoreTab();
    }
}

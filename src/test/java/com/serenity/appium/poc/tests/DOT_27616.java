package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_27616 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_27616_verifyDefaultShoppingListWithNoItemBrowsingFlow() {
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginId());

        appSteps.gotoHomeTab();
        appSteps.gotoDefaultShoppingListAndVerifyBrowseFlow();
    }

}

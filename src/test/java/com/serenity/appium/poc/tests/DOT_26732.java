package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

public class DOT_26732 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_26732_verifyCheckYourAreaForAvailabilityWithInvalidAndValidAddress() {
        appSteps.completeQuickOnboarding();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Mountain View", "Mountain View");
        appSteps.performShopThisStoreAction();

        appSteps.verifyUserIsOnHomeTab();
        appSteps.verifyCheckYourAreaForAvailabilityButtonAndClick();
        List<String> invalidAddress = Arrays.asList("6600 Rockledge Drive", "Bethesda", "MD", "20817");
        appSteps.enterDeliveryAddressAndConfirm(invalidAddress);

        List<String> validAddress = Arrays.asList("495 Viking Drive", "Pleasant Hill", "CA", "94523");
        appSteps.enterDeliveryAddressAndConfirm(validAddress);
        appSteps.verifyToastMessageAndClose("SUCCESS", "Your address has been confirmed");
        appSteps.verifyUserIsOnHomeTab();
        appSteps.verifyCheckYourAreaForAvailabilityButtonText("DELIVERING TO " + "495 Viking Drive");
    }

}

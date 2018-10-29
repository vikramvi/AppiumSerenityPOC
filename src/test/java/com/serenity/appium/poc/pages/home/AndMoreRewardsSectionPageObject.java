package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndMoreRewardsSectionPageObject extends MobilePageObject{

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[@text='&MORE REWARDS']")
    private WebElement andMoreRewardsTitle;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.Button/android.widget.TextView[@text='VIEW DETAILS']")
    private WebElement viewDetailsButton;


    public AndMoreRewardsSectionPageObject(WebDriver driver) {
        super(driver);
    }


    public boolean isAndMoreRewards_ViewDetailsButtonVisible(int waitTime){
        if(Utils.isVisible(getDriver(), viewDetailsButton, waitTime)){
            return true;
        }
        return false;
    }

    public boolean isAndMoreRewardsSectionDisplayed(){
        boolean displayed = false;
        int scrollDownCounter = 0;
        if (isAndroid()) {
            displayed = isAndMoreRewards_ViewDetailsButtonVisible(2);

            while ( !displayed && (scrollDownCounter < 4) ) {
                Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN, 0.85, 0.15);
                displayed = isAndMoreRewards_ViewDetailsButtonVisible(1);
                scrollDownCounter++;
            }
        }

        //iOS TBD

        return displayed;
    }
}

package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[@text='AVAILABLE IN THESE NEARBY STORES']")
    private WebElement pageHeader;

    public InventoryPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), pageHeader, 25)) {
            return true;
        }
        return false;
    }

    private String XPath_storesCountInCurrentView = "//android.widget.TextView[@content-desc='store-title']";
    public int getStoresCountFromInventoryPage(){

        List<String> storeNameList = new ArrayList<>();
        List<String> storeNameListWithoutDuplicates = new ArrayList<>();
        String lastItem = null;

        //initial screen
        int storesCountInCurrentScreen = getDriver().findElements(By.xpath(XPath_storesCountInCurrentView)).size();

        String XPATHPattern_FirstScreenItems = "(//android.widget.TextView[@content-desc='store-title'])[%d]";

        for (int itemCount = 1; itemCount <= storesCountInCurrentScreen; itemCount++) {
            String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);

            storeNameList.add(getDriver().findElement(By.xpath(actualXPath)).getText());
        }

        for(int tryCount = 0; tryCount < 5; tryCount++) {
            Scrolling.scrollDown(0.85, 0.15);
            Utils.waitFor(1000);

            storesCountInCurrentScreen = getDriver().findElements(By.xpath(XPath_storesCountInCurrentView)).size();


            for (int itemCount = 1; itemCount <= storesCountInCurrentScreen; itemCount++) {
                String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);
                try {
                    storeNameList.add(getDriver().findElement(By.xpath(actualXPath)).getText());
                }catch (Exception e){
                    break;
                }
            }

            if(storeNameList.get(storeNameList.size() -1).equals(lastItem)){
                break;
            }else {
                lastItem = storeNameList.get(storeNameList.size() - 1);
            }

        }

        //removes duplicates
        storeNameListWithoutDuplicates = storeNameList.stream().distinct().collect(Collectors.toList());

        return storeNameListWithoutDuplicates.size();
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='icon-orders'])[1]")
    private WebElement shopButtonAgainstTopMostStore;

    public void clickFirstStoreChangeButton(){
        shopButtonAgainstTopMostStore.click();
    }

    @AndroidFindBy(accessibility = "store-city-state-zip")
    private WebElement storyCityStateZip;
    public String getCityName(){
        String actualString = storyCityStateZip.getText();
        return  actualString.split(",")[0];
    }

}

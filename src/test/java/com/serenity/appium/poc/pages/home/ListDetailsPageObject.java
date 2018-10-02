package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListDetailsPageObject extends MobilePageObject{

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")
    private WebElement listTitle;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView")
    private WebElement StartBrowsingButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button/android.widget.TextView[2]")
    private WebElement DeleteListButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='shopping-list'][2]/android.widget.TextView")
    private WebElement DeleteListConfirmationYesButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='shopping-list'][1]/android.widget.TextView")
    private WebElement DeleteListConfirmationNoButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-floating-return']")
    private WebElement ListDetailPageReturnButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='ADD TO CART']")
    private WebElement AddToCartButtonForFirstItemUnderList;


    public ListDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isListDetailsScreenVisible(){
        return Utils.isVisible(getDriver(), listTitle, 20);
    }

    public boolean clickStartBrowsingButton(){
        if(Utils.isVisible(getDriver(), StartBrowsingButton, 15 )) {
            StartBrowsingButton.click();
            return true;
        }
        return false;
    }

    public boolean clickDeleteListButton(){
        if(Utils.isVisible(getDriver(), DeleteListButton, 15 )){
            DeleteListButton.click();
            return true;
        }
        return false;
    }

    public void clickDeleteConfirmationYesButton(){
        if(Utils.isVisible(getDriver(), DeleteListConfirmationYesButton, 5)){
            DeleteListConfirmationYesButton.click();
        }
    }

    public void clickDeleteConfirmationNoButton(){
        if(Utils.isVisible(getDriver(), DeleteListConfirmationNoButton, 5)){
            DeleteListConfirmationNoButton.click();
        }
    }

    public void clickListDetailPageReturnButton(){
        ListDetailPageReturnButton.click();
    }

    public int getListOfItemsUnderCurrentList(){
        try {
                List<String> items = new ArrayList<>();
                List<String> itemsWithDuplicates = new ArrayList<>();
                String lastItem = null;

                System.out.println("test");

                //initial screen
                String XPATHPattern_FirstScreenItems = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";
                for (int itemCount = 2; itemCount <= 4; itemCount++) {
                    String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);

                    items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
                }

                for(int tryCount = 1; tryCount < 10; tryCount++) {
                    Scrolling.scrollDown(0.85, 0.15);
                    Utils.waitFor(1000);

                    //scroll down till end
                    String XPATHPattern_ScrolledScreenItems = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";
                    for (int itemCount = 2; itemCount <= 5; itemCount++) {
                        String actualXPath = String.format(XPATHPattern_ScrolledScreenItems, itemCount);

                        try {
                            items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
                        }catch (Exception e){
                            break;
                        }

                    }

                    if(items.get(items.size() -1).equals(lastItem)){
                        break;
                    }else {
                        lastItem = items.get(items.size() - 1);
                    }
                }

                //removes duplicates
                itemsWithDuplicates = items.stream().distinct().collect(Collectors.toList());

                return itemsWithDuplicates.size();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public void clickAddToCartForFirstRowItem(){
        AddToCartButtonForFirstItemUnderList.click();
    }

    String xPathOfFirstRowItem = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";
    public String getFirstRowItemName(){
       return getDriver().findElement(By.xpath(xPathOfFirstRowItem)).getText();
    }

}

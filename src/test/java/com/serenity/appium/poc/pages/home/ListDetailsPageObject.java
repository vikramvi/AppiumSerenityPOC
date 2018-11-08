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

    @AndroidFindBy(accessibility = "text-list-title")
    private WebElement listTitle;

    @AndroidFindBy(accessibility =  "icon-edit-list-name")
    private WebElement listEditButton;

    @AndroidFindBy(accessibility = "text-shopping-list-modal-title")
    private WebElement listEditDialogTitle;

    @AndroidFindBy(accessibility = "input-shopping-list-name")
    private WebElement listEditDialogInputField;

    @AndroidFindBy(accessibility = "button-cancel-edit-or-create-shopping-list-name")
    private WebElement listEditDialogCancelButton;

    @AndroidFindBy(accessibility = "button-save-edit-or-create-shopping-list-name")
    private WebElement listEditDialogSaveButton;

    @AndroidFindBy(accessibility = "button-start-browsing")
    private WebElement StartBrowsingButton;

    @AndroidFindBy(accessibility = "button-delete-list")
    private WebElement DeleteListButton;

    @AndroidFindBy(accessibility = "approve-delete-list")
    private WebElement DeleteListConfirmationYesButton;

    @AndroidFindBy(accessibility = "decline-delete-list")
    private WebElement DeleteListConfirmationNoButton;

    @AndroidFindBy(accessibility = "button-floating-return")
    private WebElement ListDetailPageReturnButton;


    public ListDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isListDetailsScreenVisible(){
        return Utils.isVisible(getDriver(), listTitle, 20);
    }

    public void clickListEditButton(){
        listEditButton.click();
    }

    public boolean enterNewListNameAndSave(String listNewName){
        listEditDialogInputField.clear();
        listEditDialogInputField.sendKeys(listNewName);
        if(listEditDialogSaveButton.isEnabled()){
            listEditDialogSaveButton.click();
            Utils.waitFor(1000);
            return listTitle.getText().equals(listNewName);
        }
        return false;
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

    String xPathForAddToCartButtons = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text='ADD TO CART']";
    public void clickAddToCartForFirstRowItem(){
        getDriver().findElements(By.xpath(xPathForAddToCartButtons)).get(0).click();
    }

    String xPathOfFirstRowItem = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";
    public String getFirstRowItemName(){
       return getDriver().findElement(By.xpath(xPathOfFirstRowItem)).getText();
    }

}

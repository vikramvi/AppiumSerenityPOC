package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MyListsPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"header-title\"]")
    private WebElement MyListsScreenTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button/android.widget.TextView[@text='CREATE LIST']")
    private WebElement CreateListButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText")
    private WebElement ListNameEditField;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.Button/android.widget.TextView[@text='SAVE']")
    private WebElement SaveListNameButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView")
    private WebElement StartBrowsingButton;


    public MyListsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isMyListsScreenVisible(){
        return Utils.isVisible(getDriver(), MyListsScreenTitle, 20);
    }

    public boolean clickCreateListButton(){
        if(Utils.isVisible(getDriver(), CreateListButton, 5 )){
            CreateListButton.click();
            return true;
        }
        return false;
    }

    public boolean enterListName(String listName){
        if(Utils.isVisible(getDriver(), ListNameEditField, 5 )){
            ListNameEditField.sendKeys(listName);
            return true;
        }
        return false;
    }

    public boolean clickListNameSaveButton(){
        if(Utils.isVisible(getDriver(), SaveListNameButton, 5 )){
            SaveListNameButton.click();

                if( Utils.isVisible(getDriver(), CreateListButton, 30) ){
                    return true;
                }else{
                    LOGGER.error("clickListNameSaveButton failed after 30 seconds");
                }
        }
        return false;
    }


    String listsXPathPrefix = "//android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup";
    String listsXPathPostFix = "/android.view.ViewGroup/android.widget.TextView[1]";

    public boolean isListNameVisibile(String listName){
         try {

             if (Utils.isVisible(getDriver(), MyListsScreenTitle, 5) && Utils.isVisible(getDriver(), CreateListButton, 5)) {

                 List<WebElement> lists = getDriver().findElements(By.xpath(listsXPathPrefix));

                 int rowCount = 0;
                 for (int listCounter = 0; listCounter < lists.size(); listCounter++) {

                     rowCount = listCounter + 1;

                     if (getDriver().findElement(By.xpath(listsXPathPrefix + "[" + rowCount + "]" + listsXPathPostFix)).getText().equalsIgnoreCase(listName)) {
                         return true;
                     }

                 }

             }
             return false;
         }catch (Exception e){
             e.printStackTrace();
             return false;
         }
    }


    String listNameArrowXPathPrefix = "//android.view.ViewGroup/android.view.ViewGroup[";
    String listNameArrowXPathPostfix = "]/android.view.ViewGroup/android.widget.TextView[2]";

    public boolean clickArrowButtonAgainstParticularList(String listName){

        List<WebElement> lists = getDriver().findElements(By.xpath(listsXPathPrefix));

        int rowCount = 0;
        for(int listCounter = 0; listCounter < lists.size(); listCounter++ ){
            rowCount = listCounter + 1;

            if( getDriver().findElement(By.xpath(listsXPathPrefix + "[" + rowCount + "]" + listsXPathPostFix)).getText().equalsIgnoreCase(listName) ){

                getDriver().findElement(By.xpath(listNameArrowXPathPrefix + rowCount + listNameArrowXPathPostfix)).click();

                return true;
            }
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

}

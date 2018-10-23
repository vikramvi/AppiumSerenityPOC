package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MyListsPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='header-title' and @text='MY LISTS']")
    private WebElement MyListsScreenTitle;

    @AndroidFindBy(xpath="//android.widget.Button/android.widget.TextView[@text='CREATE LIST']")
    private WebElement CreateListButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.view.ViewGroup/android.widget.EditText")
    private WebElement ListNameEditField;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.Button/android.widget.TextView[@text='SAVE']")
    private WebElement SaveListNameButton;


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
    private String XPATH_PATTERN_lists =
            "//android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.TextView[1]";

    public boolean isListNameVisible(String listName){
         try {

             if (Utils.isVisible(getDriver(), MyListsScreenTitle, 5) && Utils.isVisible(getDriver(), CreateListButton, 5)) {

                 List<WebElement> lists = getDriver().findElements(By.xpath(listsXPathPrefix));

                 int rowCount = 0;
                 for (int listCounter = 0; listCounter < lists.size(); listCounter++) {

                     rowCount = listCounter + 1;

                     String xpathLists = String.format(XPATH_PATTERN_lists, rowCount);

                     if (getDriver().findElement(By.xpath(xpathLists)).getText().equalsIgnoreCase(listName)) {
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


    private String XPATH_PATTERN_listNameArrow =
            "//android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.TextView[2]";

    public boolean clickArrowButtonAgainstParticularList(String listName){

        List<WebElement> lists = getDriver().findElements(By.xpath(listsXPathPrefix));

        int rowCount = 0;
        for(int listCounter = 0; listCounter < lists.size(); listCounter++ ){
            rowCount = listCounter + 1;

            String xpathForListNameArrow = String.format(XPATH_PATTERN_listNameArrow, rowCount);
            String xpathLists = String.format(XPATH_PATTERN_lists, rowCount);

            if( getDriver().findElement(By.xpath(xpathLists)).getText().equalsIgnoreCase(listName) ){

                getDriver().findElement(By.xpath(xpathForListNameArrow)).click();

                return true;
            }
        }

        return false;
    }


    private String XPath_listsCountInCurrentView = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup";
    public int getListsCount(){

        List<String> items = new ArrayList<>();
        List<String> itemsWithoutDuplicates = new ArrayList<>();
        String lastItem = null;

        //initial screen
        int listsCountInCurrentScreen = getDriver().findElements(By.xpath(XPath_listsCountInCurrentView)).size();

        String XPATHPattern_FirstScreenItems = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.TextView[1]";

        for (int itemCount = 1; itemCount <= listsCountInCurrentScreen; itemCount++) {
            String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);

            items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
        }

        for(int tryCount = 0; tryCount < 1; tryCount++) {
            Scrolling.scrollDown(0.85, 0.15);
            Utils.waitFor(1000);

            listsCountInCurrentScreen = getDriver().findElements(By.xpath(XPath_listsCountInCurrentView)).size();


            for (int itemCount = 2; itemCount <= listsCountInCurrentScreen; itemCount++) {
                String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);

                try {
                    items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
                }catch (Exception e){
                    break;
                }

            }

        }

        //removes duplicates
        itemsWithoutDuplicates = items.stream().distinct().collect(Collectors.toList());

        return itemsWithoutDuplicates.size();
    }

}

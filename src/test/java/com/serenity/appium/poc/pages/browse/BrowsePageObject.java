package com.serenity.appium.poc.pages.browse;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public class BrowsePageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
    private WebElement BrowsePageTitle;

    @AndroidFindBy(accessibility = "browse-card-wine")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"WINE\"]")
    private WebElement TOUCHABLE_TEXT_wine;

    @AndroidFindBy(accessibility = "browse-card-spirits")
    private WebElement TOUCHABLE_TEXT_spirits;

    @AndroidFindBy(accessibility = "browse-card-beer")
    private WebElement TOUCHABLE_TEXT_beer;

    @AndroidFindBy(accessibility = "browse-card-accessories")
    private WebElement TOUCHABLE_TEXT_accessories;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='HOT PRODUCTS']")
    private WebElement HotProductsTitle;

    @AndroidFindBy(xpath="//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]//android.widget.Button/android.widget.TextView")
    private WebElement HotProductsFirstItemHeartIcon;

    @AndroidFindBy(xpath="//android.widget.Button/android.view.ViewGroup[2]/android.widget.TextView[@text='SELECT A LIST']")
    private WebElement selectAListDialogTitle_PostHeartIconClickAction;

    @AndroidFindBy(xpath="//android.widget.Button/android.view.ViewGroup[2]/android.widget.Button/android.widget.TextView[@text='VIEW ALL']")
    private WebElement selectAListDialog_PostHeartIconClickAction_ViewAllButton;


    public BrowsePageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if( Utils.isVisible(getDriver(), BrowsePageTitle, 15)){
            if(BrowsePageTitle.getText().contains("BROWSE PRODUCTS")){
                return true;
            }
        }
        return false;
    }

    public boolean isWineCardPresent() {
        return Utils.isVisible(getDriver(), TOUCHABLE_TEXT_wine, 15);
    }

    public boolean isSpiritsCardPresent() {
        return Utils.isVisible(getDriver(), TOUCHABLE_TEXT_spirits, 5);
    }

    public boolean isBeerCardPresent() {
        return Utils.isVisible(getDriver(), TOUCHABLE_TEXT_beer, 5);
    }

    public boolean isAccessoriesCardPresent() {
        return Utils.isVisible(getDriver(), TOUCHABLE_TEXT_accessories, 5);
    }

    public boolean clickWineCard() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wine);
    }

    public boolean clickSpiritsCard() {
        return Utils.tryClicking(TOUCHABLE_TEXT_spirits);
    }

    public boolean clickBeerCard() {
        return Utils.tryClicking(TOUCHABLE_TEXT_beer);
    }

    public boolean clickAccessoriesCard() { return Utils.tryClicking(TOUCHABLE_TEXT_accessories); }



    //---- W I N E    C A T E G O R I E S -------------------------------------------------------->

    @AndroidFindBy(accessibility = "browse-category-wine types")
    @iOSFindBy(accessibility = "WINE TYPES")
    private WebElement TOUCHABLE_TEXT_wineCategoryTypes;

    @AndroidFindBy(accessibility = "browse-category-varietals")
    @iOSFindBy(accessibility = "VARIETALS")
    private WebElement TOUCHABLE_TEXT_wineCategoryVarietals;

    @AndroidFindBy(accessibility = "browse-category-top countries/states")
    @iOSFindBy(accessibility = "TOP COUNTRIES/STATES")
    private WebElement TOUCHABLE_TEXT_wineCategoryTopCountriesStates;

    @AndroidFindBy(accessibility = "browse-category-popular regions")
    @iOSFindBy(accessibility = "POPULAR REGIONS")
    private WebElement TOUCHABLE_TEXT_wineCategoryPopularRegions;

    @AndroidFindBy(accessibility = "browse-category-see all wine")
    @iOSFindBy(accessibility = "SEE ALL WINE")
    private WebElement TOUCHABLE_TEXT_wineCategorySeeAll;

    public boolean clickWineTypesCategory() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineCategoryTypes);
    }

    public boolean clickWineVarietalsCategory() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineCategoryVarietals);
    }

    public boolean clickWinePopularCountriesStatesCategory() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineCategoryTopCountriesStates);
    }

    public boolean clickWinePopularRegionsCategory() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineCategoryPopularRegions);
    }

    public boolean clickSeeAllWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineCategorySeeAll);
    }



    //---- W I N E    T Y P E S    S U B C A T E G O R I E S ------------------------------------->

    @AndroidFindBy(accessibility = "browse-subcategory-red wine")
    @iOSFindBy(accessibility = "RED WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategoryRedWine;

    @AndroidFindBy(accessibility = "browse-subcategory-white wine")
    @iOSFindBy(accessibility = "WHITE WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategoryWhiteWine;

    @AndroidFindBy(accessibility = "browse-subcategory-sparkling wine & champagne")
    @iOSFindBy(accessibility = "SPARKLING WINE & CHAMPAGNE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategorySparklingWine;

    @AndroidFindBy(accessibility = "browse-subcategory-dessert & fortified wine")
    @iOSFindBy(accessibility = "DESSERT & FORTIFIED WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategoryDessertWine;

    @AndroidFindBy(accessibility = "browse-subcategory-rose & blush wine")
    @iOSFindBy(accessibility = "ROSE & BLUSH WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategoryRoseWine;

    @AndroidFindBy(accessibility = "browse-subcategory-sake & plum wine")
    @iOSFindBy(accessibility = "SAKE & PLUM WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategorySake;

    @AndroidFindBy(accessibility = "browse-subcategory-fruit wine")
    @iOSFindBy(accessibility = "FRUIT WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategoryFruitWine;

    @AndroidFindBy(accessibility = "browse-subcategory-view all wine")
    @iOSFindBy(accessibility = "VIEW ALL WINE \uE315")
    private WebElement TOUCHABLE_TEXT_wineSubcategorySeeAll;

    public boolean clickWineSubcategoryRedWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategoryRedWine);
    }

    public boolean clickWineSubcategoryWhiteWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategoryWhiteWine);
    }

    public boolean clickWineSubcategorySparklingWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategorySparklingWine);
    }

    public boolean clickWineSubcategoryDessertWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategoryDessertWine);
    }

    public boolean clickWineSubcategoryRoseWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategoryRoseWine);
    }

    public boolean clickWineSubcategorySake() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategorySake);
    }

    public boolean clickWineSubcategoryFruitWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategoryFruitWine);
    }

    public boolean clickWineSubcategoryAllWine() {
        return Utils.tryClicking(TOUCHABLE_TEXT_wineSubcategorySeeAll);
    }



    public boolean isHotProductsTitleDisplayed(){
        if( Utils.isVisible(getDriver(), HotProductsTitle, 20)){
            return true;
        }else{
            LOGGER.error("HOT PRODUCTS section didn't show up even after 20 seconds");
            return false;
        }
    }

    public boolean isHotProductsFirstItemHeartIconVisible(){
        return Utils.isVisible(getDriver(), HotProductsFirstItemHeartIcon, 2);
    }

    public void clickFirstItemHeartIcon(){
        HotProductsFirstItemHeartIcon.click();
    }

    public String getFirtItemNameWithHeartIcon(){

        return  getDriver().findElement(By.xpath("//android.widget.TextView[@content-desc=\"product-name\"][1]")).getText();
    }

    public boolean scrollToHotProductsSection() {
        boolean displayed = false;
        int scrollDownCounter = 0;

        if (isAndroid()) {

            if(isHotProductsTitleDisplayed()) {

                displayed = isHotProductsFirstItemHeartIconVisible();

                    while (!displayed && (scrollDownCounter < 4)) {
                        Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                        displayed = isHotProductsFirstItemHeartIconVisible();
                        scrollDownCounter++;
                    }
            }
        }

        //iOS TBD

        return displayed;
    }

    public boolean isSelectAListDialogTitle_PostHeartIconClickActionVisible(){
       return Utils.isVisible(getDriver(), selectAListDialogTitle_PostHeartIconClickAction, 15);
    }

    public boolean addItemByClickingHeartIconToList(String listName){
        try{
                String listNameXpathPreFix = "//android.widget.Button/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[";
                String listNameXpathPostFix = "]/android.view.ViewGroup/android.widget.TextView";
                String listNameButtonXpathPostFix = "]/android.view.ViewGroup/android.widget.Button";


                if (isSelectAListDialogTitle_PostHeartIconClickActionVisible()) {

                    String XPath_CurrentViewLists = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup";
                    int listCountInCurrentView = getDriver().findElements(By.xpath(XPath_CurrentViewLists)).size();

                    for (int listCount = 1; listCount <= listCountInCurrentView; listCount++) {

                        if (getDriver().findElement(By.xpath(listNameXpathPreFix + listCount + listNameXpathPostFix)).getText().equalsIgnoreCase(listName)) {

                            getDriver().findElement(By.xpath(listNameXpathPreFix + listCount + listNameButtonXpathPostFix)).click();

                            //TEMP FIX
                            //Issue there is no attribute which changes after clicking on checkmark
                            Utils.waitFor(3000);

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

    public void clickViewAllButton(){
        selectAListDialog_PostHeartIconClickAction_ViewAllButton.click();
    }

    public boolean verifyItemNameAddedToTheList(String expectedItemName){

        String itemUnderListXPath = "//android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";

        if( Utils.isVisible(getDriver(), By.xpath(itemUnderListXPath), 15)){

            String actualItemName = getDriver().findElement(By.xpath(itemUnderListXPath)).getText();

//            return actualItemName.equalsIgnoreCase(expectedItemName);
            return expectedItemName.contains(actualItemName); // list name may drop vintage
        }

        return false;
    }

}


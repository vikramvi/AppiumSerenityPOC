package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowsePageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "browse-card-wine")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"WINE\"]")
    private WebElement TOUCHABLE_TEXT_wine;

    @AndroidFindBy(accessibility = "browse-card-spirits")
    private WebElement TOUCHABLE_TEXT_spirits;

    @AndroidFindBy(accessibility = "browse-card-beer")
    private WebElement TOUCHABLE_TEXT_beer;

    @AndroidFindBy(accessibility = "browse-card-accessories")
    private WebElement TOUCHABLE_TEXT_accessories;

    public BrowsePageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isWineCardPresent() {
        return Utils.isVisible(getDriver(), TOUCHABLE_TEXT_wine, 5);
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
}


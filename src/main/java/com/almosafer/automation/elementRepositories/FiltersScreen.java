package com.almosafer.automation.elementRepositories;

import com.almosafer.automation.base.BaseCommonActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class FiltersScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Filters']")
    private MobileElement txtFiltersInTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price range']")
    private MobileElement txtPriceRange;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.travel.almosafer:id/tvPriceRangeBarFrom']")
    private MobileElement valStartingFlightPriceRange;
    @AndroidFindBy(xpath = "//android.widget.SeekBar[@resource-id='com.travel.almosafer:id/priceRangeSeekBar']")
    private MobileElement rangeSeekBar;
    @AndroidFindBy(id = packageName +  ":id/btnApplyFilter")
    private MobileElement btnApplyFilter;

    /**
     * Description: Validate filter screen title
     */
    public void verifyFiltersScreenTitle(){
        Assert.assertEquals(getText(txtFiltersInTitle,"Filters Title"),"Filters","Filters title is not matched");
    }
    /**
     * Description: Validate the text of Price range
     */
    public void verifyStartingPriceWithLowestPrice(){
        Assert.assertEquals(getText(txtPriceRange,"Validate filter option"),"Price range","Filters - Price range is not matched");
        String actualStartingPriceWithDecimal = getText(valStartingFlightPriceRange,"Starting flight fare");
        String[] actualStartingPrice = actualStartingPriceWithDecimal.split("\\.");
        Assert.assertEquals(actualStartingPrice[0],oCommonMap.get("firstCheapestFlightFare"),"Lowest price value is not matched");
    }
}

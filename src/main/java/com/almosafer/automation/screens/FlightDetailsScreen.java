package com.almosafer.automation.screens;

import com.almosafer.automation.base.BaseCommonActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class FlightDetailsScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";
    public int setMultiCheckEntries = 3;

    @AndroidFindBy(id = packageName +":id/tvFlightToolbarTitle")
    private List<MobileElement> txtFlightDetailTitle;
    @AndroidFindBy(id = packageName + ":id/tvFlightToolbarSubTitle")
    private MobileElement valTotalTravellersAndTravelDate;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sort by']")
    private MobileElement txtSortBy;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Filters']")
    private MobileElement txtFilters;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cheapest']")
    private MobileElement txtCheapest;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.travel.almosafer:id/tvTitleView' and @text='Sort by']")
    private MobileElement txtSortByBottomUpLayer;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Lowest price']")
    private MobileElement txtLowestPriceBottomUpLayer;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.travel.almosafer:id/tvFinalPrice'])[1]")
    private MobileElement valFirstFlightFare;

    /**
     * Description: Validate Flight detail title
     */
    public void verifyFlightDetailTitle(){
        ArrayList <String> actualTitle = new ArrayList<>();
        for (MobileElement ml : txtFlightDetailTitle){
            actualTitle.add(getText(ml, "Flight Detail"));
        }
        Assert.assertEquals(actualTitle.get(0),oCommonMap.get("airportCodeOrigin"),"Origin AirCode is not matched in title");
        Assert.assertEquals(actualTitle.get(1),oCommonMap.get("airportCodeDestination"),"Destination AirCode is not matched in title");
        Assert.assertEquals(actualTitle.get(2).substring(2),oCommonMap.get("travelOptionRoundTrip"),"Round Trip is not matched in title");

        waitForVisibility(txtSortBy);
    }
    /**
     * Description: Validate total traveller and dates
     */
    public void verifyTravellerAndDates(){
        Assert.assertEquals(getText(valTotalTravellersAndTravelDate,"Subtitle"),oCommonMap.get("totalTravelersCount")+" Travellers - "+oCommonMap.get("departureTimeline").toString().substring(0,6)+" - "+oCommonMap.get("returnTimeLine").toString().substring(0,6));
    }
    /**
     * Description: Validate and Click on sort by
     */
    public void verifyAndClickSortBy(){
        Assert.assertTrue(isDisplayed(txtSortBy,"Is displayed"), "Sort By option is not displayed");
        Assert.assertEquals(getText(txtSortBy,"Validate text"),"Sort by","Sort By text is not matched");
        click(txtSortBy,"Click on Sort by");
    }
    /**
     * Description: Validate and Click on Lowest price in bottom up slide
     */
    public void verifyAndClickLowestPrice(){
        Assert.assertEquals(getText(txtSortByBottomUpLayer,"Validate the title in bottom layer"),"Sort by","Sort by title is not matched");
        Assert.assertEquals(getText(txtLowestPriceBottomUpLayer,"Validate the option in bottom layer"),"Lowest price","Lowest Price option text is not matched");
        click(txtLowestPriceBottomUpLayer,"Click on Lowest Price");
    }
    /**
     * Description: Validate Lowest Price selected is present in the filter section
     */
    public void verifyLowestPriceInFilter(){
        Assert.assertEquals(getText(txtLowestPriceBottomUpLayer,"Validate selected option"),"Lowest price","Lowest Price title text is not matched");
        Assert.assertEquals(getText(txtCheapest,"Validate label"),"Cheapest","Section is not having Cheapest heading");
    }
    /**
     * Description: Save the first cheapest flight
     */
    public void fetchAndSaveCheapestFlight(){
        String firstCheapestFlightFare = getText(valFirstFlightFare,"First flight fare details");
        oCommonMap.put("firstCheapestFlightFare",firstCheapestFlightFare);
    }
    /**
     * Description: Compare first cheapest flight with multiple flights by scrolling down
     */
    public void compareFirstCheapestFlightWithMultipleFlights(){
        do{
            scrollDown();
            MobileElement multiPriceEl = scrollForwardToTextContains("AED");
            Integer cheapestPrice = Integer.parseInt( oCommonMap.get("firstCheapestFlightFare").toString().replace("AED", "").replaceAll("[^\\d.]", "").trim());
            String multiPrice =getText(multiPriceEl,"Compare the cheapest flight price with multiple flight price AED " + cheapestPrice + " <");
            assert cheapestPrice < Integer.parseInt(multiPrice.replace("AED", "").replaceAll("[^\\d.]", "").trim());
            setMultiCheckEntries--;
        }while (setMultiCheckEntries>0);
    }
    /**
     * Description: Validate and click on filters field
     */
    public void verifyAndClickFilters(){
        Assert.assertEquals(getText(txtFilters,"Validate text"),"Filters","Filters text is not matched");
        click(txtFilters,"Click on Filters");
    }
}

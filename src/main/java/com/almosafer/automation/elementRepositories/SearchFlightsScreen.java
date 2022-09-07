package com.almosafer.automation.elementRepositories;

import com.almosafer.automation.base.BaseCommonActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

/**
 * Project Almosafer
 * @author Nandhini
 */
public class SearchFlightsScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Search flights']")
    private MobileElement txtSearchTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Round-trip']")
    private MobileElement txtRoundTrip;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='From']")
    private MobileElement txtFrom;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='From']/following-sibling::android.widget.TextView")
    private MobileElement valFrom;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='To']")
    private MobileElement txtTo;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='To']/following-sibling::android.widget.TextView")
    private MobileElement valTo;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Departure date']")
    private MobileElement lblDepartureDate;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Departure date']/following-sibling::android.widget.TextView")
    private MobileElement valDepartureDate;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Return date']")
    private MobileElement lblReturnDate;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Return date']/following-sibling::android.widget.TextView")
    private MobileElement valReturnDate;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Passengers and cabin class']")
    private MobileElement txtPaxAndCabinClass;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Passengers and cabin class']/following-sibling::android.widget.TextView")
    private MobileElement txtTotalTravellersAndCabinClass;
    @AndroidFindBy(id = packageName +":id/btnFlightSearch")
    private MobileElement btnFindFlights;

    /**
     * Description: Validate Search screen title
     */
    public void verifySearchScreenTitle(){
        Assert.assertEquals(getText(txtSearchTitle,"Title validation"),"Search "+oCommonMap.get("businessOption").toString().toLowerCase(),"Entered into a different screen : Title is not matched");
    }
    /**
     * Description: Validate Round Trip is selected
     */
    public void verifyRoundTripAndSelectIfNot(){
        String roundTrip = getText(txtRoundTrip, "Trip option ");
        Assert.assertEquals(roundTrip,"Round-trip", "Round Trip option text is not matched");
        boolean isSelected = Boolean.parseBoolean(getAttribute(txtRoundTrip,"selected"));
        if (!isSelected)
            click(txtRoundTrip,"Round trip clicked");
        oCommonMap.put("travelOptionRoundTrip",roundTrip);
    }
    /**
     * Description: Click on From option to enter the airport code
     */
    public void clickFromOption(){
        click(txtFrom, "Click on Origin field");
    }
    /**
     * Description: Validate the selected Airport location and airport code
     */
    public void verifySelectedAirportLocationAndCode(){
        String actualFromValue = getText(valFrom,"Selected Origin Airport Name and Code ");
        Assert.assertEquals(actualFromValue,oCommonMap.get("airportCodeOrigin")+" - "+oCommonMap.get("originAirportLocationName"));
        String actualToValue = getText(valTo,"Selected Departure Airport Name and Code ");
        Assert.assertEquals(actualToValue,oCommonMap.get("airportCodeDestination")+" - "+oCommonMap.get("departureAirportLocationName"));
    }
    /**
     * Description: Validate the date present in departure date field
     */
    public void verifyDefaultDepartureDate(){
        Assert.assertEquals(getText(lblDepartureDate, "Label name"),"Departure date","Departure date field label text is not matched");
        oCommonMap.put("initialDepartureDate",getNextDayDate(1));
        Assert.assertEquals(getText(valDepartureDate,"Departure date initial value"), oCommonMap.get("initialDepartureDate"), "Departure date initial value is not matched");
    }
    /**
     * Description: Validate the date present in return date field
     */
    public void verifyDefaultReturnDate(){
        Assert.assertEquals(getText(lblReturnDate, "Label name"),"Return date","Return date field label text is not matched");
        oCommonMap.put("initialReturnDate",getNextDayDate(2));
        Assert.assertEquals(getText(valReturnDate,"Return date initial value"), oCommonMap.get("initialReturnDate"), "Return date initial value is not matched");
    }
    /**
     * Description: Click on departure date label to navigate to next screen
     */
    public void clickDepartureDateLabel(){
        click(lblDepartureDate, "Click on Departure date");
    }
    /**
     * Description: Validate selected departure date
     */
    public void verifySelectedDepartureDate(){
        Assert.assertEquals(getText(valDepartureDate,"Departure date selected value"), getDateMonthYearForGivenDate(oCommonMap.get("departureTimeline").toString()), "Departure date selected value is not matched");
    }
    /**
     * Description: Validate selected return date
     */
    public void verifySelectedReturnDate(){
        Assert.assertEquals(getText(valReturnDate,"Return date selected value"), getDateMonthYearForGivenDate(oCommonMap.get("returnTimeLine").toString()), "Return date selected value is not matched");
    }
    /**
     * Description: Validate default value of traveller
     */
    public void verifyDefaultPaxAndCabin(){
        Assert.assertEquals(getText(txtPaxAndCabinClass,"Label validation"), "Passengers and cabin class","Passengers and cabin class label is not matched");
        Assert.assertEquals(getText(txtTotalTravellersAndCabinClass,"Default traveller"),"1 Adult - Economy","Total traveller value is not matched");
    }
    /**
     * Description: Click on Passengers and cabin class to navigate to next screen
     */
    public void clickPaxAndCabin(){
        click(txtPaxAndCabinClass,"Click on Passengers and Cabin class option");
    }
    /**
     * Description: Validate total travellers after updating total travellers
     */
    public void verifySelectedTravellersTotalCountAndCabin(){
        waitForVisibility(txtTotalTravellersAndCabinClass);
        Assert.assertEquals(getText(txtTotalTravellersAndCabinClass,"Total Traveller and cabin class"),oCommonMap.get("totalTravelersCount")+" Travellers - "+oCommonMap.get("cabinClass"),"Total Traveller and cabin class is not matched");
    }
    /**
     * Description: Click on Find Flights to select the flight to travel
     */
    public void clickFindFlights(){
        click(btnFindFlights,"Click on Find Flights Button");
    }
}

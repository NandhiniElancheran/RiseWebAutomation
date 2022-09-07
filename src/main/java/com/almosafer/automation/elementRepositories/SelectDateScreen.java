package com.almosafer.automation.elementRepositories;

import com.almosafer.automation.base.BaseCommonActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class SelectDateScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select dates']")
    private MobileElement txtSelectDateTitle;
    @AndroidFindBy(id = packageName +":id/fromDateTV")
    private MobileElement txtDepartureDate;
    @AndroidFindBy(id = packageName +":id/fromDayOfWeekTv")
    private MobileElement txtDepartureDayLabel;
    @AndroidFindBy(id = packageName + ":id/toDateTitle")
    private MobileElement txtReturnLabel;
    @AndroidFindBy(id = packageName +":id/toDateTV")
    private MobileElement txtReturnDate;
    @AndroidFindBy(id = packageName +":id/toDayOfWeekTv")
    private MobileElement txtReturnDayLabel;
    @AndroidFindBy(id = packageName +":id/confirmTV")
    private MobileElement btnConfirm;
    @AndroidFindBy(id = packageName +":id/extraSubtitle")
    private MobileElement selectedTripTxtOnConfirm;

    /**
     * Description: Validate Select Date screen title
     */
    public void verifySelectDateScreenTitle(){
        Assert.assertEquals(getText(txtSelectDateTitle,"Title validation"),"Select dates","Entered into a different screen : Title is not matched");
    }
    /**
     * Description: Select the travel departure date from calendar
     */
    public void selectTravelDepartureDate(String date,String month, String year){
        super.scrollForwardToText(month.toUpperCase() +" "+year);
        super.scrollToGivenDateAndSelect(date, month.toUpperCase(), year);
        oCommonMap.put("departureTimeline",date+" "+month+" "+year);
    }
    /**
     * Description: Validate select return date text
     */
    public void verifySelectReturnDateText(){
        Assert.assertEquals(getText(txtReturnDate,"Validate Return tab text"),"Select return date","Select Return date is not matched");
    }
    /**
     * Description: Select the travel return date from calendar
     */
    public void selectTravelReturnDate(String date,String month, String year){
        super.scrollForwardToText(month.toUpperCase() +" "+year);
        super.scrollToGivenDateAndSelect(date, month.toUpperCase(), year);
        oCommonMap.put("returnTimeLine",date+" "+month+" "+year);
    }
    /**
     * Description: Select the travel return date from calendar
     */
    public void verifySelectedTravelDates(){
        Assert.assertEquals(getText(txtDepartureDate,"Departure date text"),oCommonMap.get("departureTimeline"),"Departure Travel date is not matched");
        String departureDay = getDayForGivenDate(oCommonMap.get("departureTimeline").toString());
        Assert.assertEquals(getText(txtDepartureDayLabel,"Departure Day text"),departureDay,"Departure day text is not matched");
        Assert.assertEquals(getText(txtReturnDate,"Return date text"),oCommonMap.get("returnTimeLine").toString(),"Return Travel date is not matched");
        String returnDay = getDayForGivenDate(oCommonMap.get("returnTimeLine").toString());
        Assert.assertEquals(getText(txtReturnDayLabel,"Return Day text"), returnDay,"Return day text is not matched");
    }
    /**
     * Description: Validate buttons displayed and click on Confirm button
     */
    public void verifyConfirmAndSelect(){
        Assert.assertTrue(isDisplayed(btnConfirm,"Confirm button displayed"),"Confirm button is not displayed");
        Assert.assertEquals(getText(selectedTripTxtOnConfirm,"Trip option text"),oCommonMap.get("travelOptionRoundTrip").toString(),"Trip Option is not matched");
        click(btnConfirm,"Click on Confirm button");
    }
}

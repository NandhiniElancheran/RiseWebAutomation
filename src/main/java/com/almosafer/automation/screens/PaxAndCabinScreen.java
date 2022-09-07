package com.almosafer.automation.screens;

import com.almosafer.automation.base.BaseCommonActions;
import com.almosafer.automation.base.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class PaxAndCabinScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Passengers and cabin class']")
    private MobileElement txtPaxAndCabinTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Adults']")
    private MobileElement txtAdults;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Children']")
    private MobileElement txtChildren;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Infants']")
    private MobileElement txtInfants;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxAdult']//following-sibling::android.widget.ImageView[2]")
    private MobileElement btnPlusAdult;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxChild']//following-sibling::android.widget.ImageView[2]")
    private MobileElement btnPlusChild;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxInfants']//following-sibling::android.widget.ImageView[2]")
    private MobileElement btnPlusInfants;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxAdult']//following-sibling::*[@resource-id='com.travel.almosafer:id/count']")
    private MobileElement txtCountAdult;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxChild']//following-sibling::*[@resource-id='com.travel.almosafer:id/count']")
    private MobileElement txtCountChild;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.travel.almosafer:id/paxInfants']//following-sibling::*[@resource-id='com.travel.almosafer:id/count']")
    private MobileElement txtCountInfants;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Apply']")
    private MobileElement txtApply;

    /**
     * Description: Validate Passengers and cabin class title
     */
    public void verifyPaxAndCabinTitle(){
        Assert.assertEquals(getText(txtPaxAndCabinTitle,"Passengers and Cabin Class title"),"Passengers and cabin class","Title is not matched");
    }
    /**
     * Description: Validate Adults text and add number of adults by click on plus button
     * @param: How many adult are travelling need to passed from test data file
     */
    public void verifyAdultsAndAddNumOfAdults(String adultCount){
        Assert.assertEquals(getText(txtAdults,"Adults section"),"Adults","Adults text is not matched");
        String actualAdultCount = getText(txtCountAdult,"Adults default count");
        Assert.assertEquals(actualAdultCount,"1","Adults count is not matched");
        if (Integer.parseInt(actualAdultCount) == Integer.parseInt(adultCount))
             oCommonMap.put("adultCount",actualAdultCount);
    }
    /**
     * Description: Validate Children text and add number of children by click on plus button
     * @param: How many child are travelling need to passed from test data file
     */
    public void verifyChildrenAndAddNumOfChildren(String childrenCount){
        Assert.assertEquals(getText(txtChildren,"Children section"),"Children","Children text is not matched");
        String actualChildrenCount = getText(txtCountChild,"Children default count");
        Assert.assertEquals(actualChildrenCount,"0","Children count is not matched");
        if (Integer.parseInt(actualChildrenCount) == 0){
            for (int i = 1;i<=Integer.parseInt(childrenCount);i++){
                click(btnPlusChild,"Click on Children plus button to increment the count to " + i);
            }
        }
        oCommonMap.put("childrenCount",getText(txtCountChild,"Children new count"));
    }
    /**
     * Description: Validate infant text and add number of infant by click on plus button
     * @param: How many infant are travelling need to passed from test data file
     */
    public void verifyInfantsAndAddNumOfInfant(String infantsCount){
        Assert.assertEquals(getText(txtInfants,"Infants section"),"Infants","Infants text is not matched");
        String actualInfantsCount = getText(txtCountInfants,"Infants default count");
        Assert.assertEquals(actualInfantsCount,"0","Infants count is not matched");
        if (Integer.parseInt(actualInfantsCount) == 0){
            for (int i = 1;i<=Integer.parseInt(infantsCount);i++){
                click(btnPlusInfants,"Click on Infants plus button to increment the count to "+ i);
            }
        }
        oCommonMap.put("infantsCount",getText(txtCountInfants,"Infants new count"));
    }
    /**
     * Description: Select the cabin class to travel
     * @param: Pass the cabin class to travel from test data file
     */
    public void clickCabinClass(String cabin){
        MobileElement cabinClass = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.RadioButton[@resource-id='com.travel.almosafer:id/rdCabinItem' and @text ='"+cabin+"']"));
        String cabinSelection = getText(cabinClass,"Cabin class selection");
        Assert.assertEquals(cabinSelection,"Economy","Economy cabin class text is not matched");
        Boolean isSelected = Boolean.valueOf(getAttribute(cabinClass,"checked"));
        if (!isSelected)
            click(cabinClass,"Click on cabin class");
        oCommonMap.put("cabinClass",cabinSelection);
    }
    /**
     * Description: Click on Apply
     */
    public void clickApply(){
        this.getTotalTravellersCount();
        click(txtApply,"Click on Apply Button");
    }
    /**
     * Description: Count all the travellers
     */
    private void getTotalTravellersCount(){
        Integer totalCount = Integer.parseInt(oCommonMap.get("adultCount").toString())+
                Integer.parseInt(oCommonMap.get("childrenCount").toString())+
                Integer.parseInt(oCommonMap.get("infantsCount").toString());
        oCommonMap.put("totalTravelersCount",totalCount.toString());
    }
}

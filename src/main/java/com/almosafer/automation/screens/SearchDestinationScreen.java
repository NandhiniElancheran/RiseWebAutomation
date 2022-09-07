package com.almosafer.automation.screens;

import com.almosafer.automation.base.BaseCommonActions;
import com.almosafer.automation.base.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class SearchDestinationScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Search Destination']")
    private MobileElement txtSearchDestination;
    @AndroidFindBy(id = packageName + ":id/edSearch")
    private MobileElement inputSearchBar;
    @AndroidFindBy(id = packageName + ":id/imgClearSearch")
    private MobileElement imgClearSearch;

    /**
     * Description: Validate Search Destination title
     */
    public void verifySearchDestinationTitle() {
        Assert.assertEquals(getText(txtSearchDestination,"Title validation"),"Search Destination", "Search Destination title is not matched");
    }
    /**
     * Description: Enter Airport Code in input bar
     * @param: Airport code need to given in test data file and will be entered by random
     */
    public HashMap<Object,Object> enterAirportCodeInSearch(String[] airportCode){
        String airCode = getRandom(airportCode);
        clear(inputSearchBar, "Clear the entered text");
        sendKeys(inputSearchBar,airCode,"Enter Airport Code");
        oCommonMap.put("airportCodeDestination",airCode);
        return oCommonMap;
    }
    /**
     * Description: Validate and click on destination airport code from the list
     */
    public void verifyAndClickDestinationAirCode(){
        MobileElement elAirportCode = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.travel.almosafer:id/tvAirportCode' and @text ='"+oCommonMap.get("airportCodeDestination")+"']"));
        Assert.assertEquals(getText(elAirportCode,"Text of Origin Airport Code in the shown list"),oCommonMap.get("airportCodeDestination"),"Destination Airport Code is not found in the search list");
        MobileElement elAirportLocationName = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text ='"+oCommonMap.get("airportCodeDestination")+"']//preceding-sibling::android.widget.TextView[@resource-id='com.travel.almosafer:id/tvAirportLocationName']"));
        oCommonMap.put("departureAirportLocationName",getText(elAirportLocationName,"Departure Airport name "));
        click(elAirportCode, "Selected the Destination Airport Code");
    }
}

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
public class SearchOriginScreen extends BaseCommonActions {
    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Search Origin']")
    private MobileElement txtSearchOrigin;
    @AndroidFindBy(id = packageName + ":id/edSearch")
    private MobileElement inputSearchBar;

    /**
     * Description: Validate Search Origin screen title
     */
    public void verifySearchOriginTitle() {
        Assert.assertEquals(getText(txtSearchOrigin,"Title validation"),"Search Origin", "Search origin title is not matched");
    }
    /**
     * Description: Enter the airport code
     * @param: Airport code need to pass from test data file and will be entered by random
     */
    public HashMap<Object,Object> enterAirportCodeInSearch(String[] airportCode){
        String airCode = getRandom(airportCode);
        clear(inputSearchBar, "Clear the entered text");
        sendKeys(inputSearchBar,airCode,"Enter Airport Code");
        oCommonMap.put("airportCodeOrigin",airCode);
        return oCommonMap;
    }
    /**
     * Description: Validate and click on origin airport code from the list
     */
    public void verifyAndClickOriginAirCode(){
        MobileElement elAirportCode = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.travel.almosafer:id/tvAirportCode' and @text ='"+oCommonMap.get("airportCodeOrigin")+"']"));
        Assert.assertEquals(getText(elAirportCode,"Text of Origin Airport Code in the shown list"),oCommonMap.get("airportCodeOrigin"),"Origin Airport Code is not found in the search list");
        MobileElement elAirportLocationName = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text ='"+oCommonMap.get("airportCodeOrigin")+"']//preceding-sibling::android.widget.TextView[@resource-id='com.travel.almosafer:id/tvAirportLocationName']"));
        oCommonMap.put("originAirportLocationName",getText(elAirportLocationName,"Selected Origin Airport Location name "));
        click(elAirportCode, "Selected the Origin Airport Code");
    }
}

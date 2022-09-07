package com.almosafer.automation.elementRepositories;

import com.almosafer.automation.base.BaseCommonActions;
import com.almosafer.automation.base.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
/**
 * Project Almosafer
 * @author Nandhini
 */
public class HomeScreen extends BaseCommonActions {

    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(id = packageName +  ":id/title")
    private MobileElement txtHomeTitle;
    @AndroidFindBy(id = packageName + ":id/half_interstitial_image")
    private MobileElement welcomePopup;

    /**
     * Description: Click on Popup present on home screen
     */
    public void clickOnWelcomePopup(){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(packageName +":id/half_interstitial_image")));
        int size = DriverManager.getDriver().findElements(By.id(packageName +":id/half_interstitial_image")).size();
        if(size>0){
            click(welcomePopup, "Closing the popup");
        }
    }
    /**
     * Description: Validate home screen title
     */
    public void verifyHomeScreenTitle(){
        String welcomeTitle = getText(txtHomeTitle, "Title validation");
        Assert.assertEquals(welcomeTitle,"Let’s book your next trip", "Home title is not matched");
    }
    /**
     * Description:: Click on Flight or Hotel option as per the requirement and passed in parameter
     * @param: Flight or Hotel option need to passed from test data file
     */
    public void clickFlightOrHotelOptions(String option){
        MobileElement selectTravelOptions = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+option+"']"));
        String selectedOption=getText(selectTravelOptions,"Choose the travel option");
        click(selectTravelOptions, "Click on Flights in home screen");
        oCommonMap.put("businessOption",selectedOption);
    }
}

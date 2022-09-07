package com.almosafer.automation.elementRepositories;

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
public class WelcomeScreen extends BaseCommonActions {

    public final String packageName = "com.travel.almosafer";

    @AndroidFindBy(id = packageName +  ":id/title")
    private MobileElement txtWelcomeTitle;
    @AndroidFindBy(id = packageName +  ":id/welcomeEnglishButton")
    private MobileElement btnEnglish;
    @AndroidFindBy(id = packageName + ":id/ctaButton")
    private MobileElement btnContinue;

    /**
     * Description: Select English Language if its not selected by default
     */
    public void verifyAndClickLanguage(){
        boolean isSelected = Boolean.parseBoolean(getAttribute(btnEnglish,"selected"));
        Assert.assertEquals(getText(btnEnglish, "Select language"),"English","Button text is not matched");
        if (!isSelected){
            click(btnEnglish,"Select Language button" );
        }
    }
    /**
     * Description: Verify Welcome screen title
     */
    public void verifyScreenTitle(){
        String welcomeTitle = getText(txtWelcomeTitle, "Title validation");
        Assert.assertEquals(welcomeTitle.trim(),"Welcome traveller!", "Welcome title is not matched");
    }
    /**
     * Description: Select the country
     * @param countryName
     */
    public void verifyAndSelectCounty(String countryName){
        MobileElement actualCountryNameEl = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.travel.almosafer:id/posRowTitle' and @text='"+countryName+"']"));
        String actualCountryName = getText(actualCountryNameEl,"Get country name");
        Assert.assertEquals(actualCountryName, countryName, "Country Name is not present");
        click(actualCountryNameEl, "Click on "+countryName+" from list of countries");
    }
    /**
     * Description: Click on Continue button
     */
    public void clickContinue(){
        click(btnContinue,"Click on Continue button");
    }
}

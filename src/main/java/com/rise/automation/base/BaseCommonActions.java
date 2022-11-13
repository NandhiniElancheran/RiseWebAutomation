package com.rise.automation.base;

import com.aventstack.extentreports.Status;
import com.rise.automation.reports.ExtentReport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Nandhini
 * description: All the common action methods used in project
 */
public class BaseCommonActions {
    public WebDriverWait wait;
    private static final Duration WAIT_TIME = Duration.ofSeconds(10);
    public static final Logger logger=Logger.getLogger(BaseCommonActions.class.getName());
    public static HashMap<Object,Object> oCommonMap = new HashMap<Object,Object>();

    public BaseCommonActions() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        wait = new WebDriverWait(DriverManager.getDriver(), WAIT_TIME);
    }
    public void waitForVisibility(WebElement e) {
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);
        e.click();
    }
    public void clear(WebElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);
        e.clear();
    }
    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg+" : "+ txt);
        logger.info(msg +" : "+ txt);
        e.sendKeys(txt);
    }
    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute).trim();
    }
    public String getText(WebElement e, String msg) {
        String txt = getAttribute(e, "text");
        ExtentReport.getTest().log(Status.INFO, msg +" : "+ txt);
        logger.info(msg +" : "+txt);
        return txt.trim();
    }
    public Boolean isDisplayed(WebElement e, String msg) {
        waitForVisibility(e);
        Boolean result = e.isDisplayed();
        ExtentReport.getTest().log(Status.INFO, msg +" : "+ result);
        logger.info(msg +" : "+ result);
        return result;
    }

    public String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public String getPageTitle(String msg){
        String txt = DriverManager.getDriver().getTitle().trim();
        ExtentReport.getTest().log(Status.INFO, msg + txt);
        logger.info(msg +txt);
        return txt;
    }

}

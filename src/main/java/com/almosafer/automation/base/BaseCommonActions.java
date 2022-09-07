package com.almosafer.automation.base;

import com.aventstack.extentreports.Status;
import com.almosafer.automation.reports.ExtentReport;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Nandhini
 * description: All the common action methods used in project
 */
public class BaseCommonActions {
    public WebDriverWait wait;
    private static final int WAIT_TIME = 45;
    public static final Logger logger=Logger.getLogger(BaseCommonActions.class.getName());
    public static HashMap<Object,Object> oCommonMap = new HashMap<Object,Object>();

    public BaseCommonActions() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        wait = new WebDriverWait(DriverManager.getDriver(), WAIT_TIME);
    }
    public void waitForVisibility(MobileElement e) {
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);
        e.click();
    }
    public void clear(MobileElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);
        e.clear();
    }
    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg+" : "+ txt);
        logger.info(msg +" : "+ txt);
        e.sendKeys(txt);
    }
    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute).trim();
    }
    public String getText(MobileElement e, String msg) {
        String txt = getAttribute(e, "text");
        ExtentReport.getTest().log(Status.INFO, msg +" : "+ txt);
        logger.info(msg +" : "+txt);
        return txt.trim();
    }
    public Boolean isDisplayed(MobileElement e, String msg) {
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

    public String getNextDayDate(int day) {
        return LocalDate.now().plus(Period.ofDays(day)).format(DateTimeFormatter.ofPattern("dd MMM, yyyy"));
    }

    public String getDayForGivenDate(String dateInput){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        Date date= null;
        try {
            date = sdf.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("EEEE");
        String dayOfGivenDate = sdf.format(date);
        return dayOfGivenDate;
    }

    public String getDateMonthYearForGivenDate(String dateInput){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        Date date= null;
        try {
            date = sdf.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("dd MMM, yyyy");
        String dayOfGivenDate = sdf.format(date);
        return dayOfGivenDate;
    }

    public void scrollForwardToText(String text) {
        try {
            DriverManager.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward().scrollIntoView(new UiSelector().text(\"" +text+ "\"));"));
        } catch (ElementNotVisibleException env) {
            Reporter.log("Element is not visible even after scrolling to the bottom of the page " + text);
        }
    }

    public MobileElement scrollForwardToTextContains(String text){
        MobileElement el = null;
        try {
            el = (MobileElement) DriverManager.getDriver().findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10).scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
        }catch (ElementNotVisibleException env) {
            Reporter.log("Element is not visible even after scrolling to the bottom of the page " );
        }
        return el;
    }

    public void scrollDown() {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startx = (int) (size.width / 2);
        int starty = (int) (size.height / 2);
        int endX = startx;
        int endY = starty - 900;
        TouchAction ts = new TouchAction(DriverManager.getDriver());
        ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }
    public void scrollToGivenDateAndSelect(String date, String month, String year) {
        int dateCount = DriverManager.getDriver().findElements(By.xpath("//android.widget.TextView[@text='" + month + " " + year + "']/following-sibling::\t\n" +
                        "ni.a//following-sibling::ni.b/child::android.widget.CheckedTextView[@text='" + date + "']")).size();
        if (dateCount > 0) {
            DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + month + " " + year + "']/following-sibling::\t\n" +
                    "ni.a//following-sibling::ni.b/child::android.widget.CheckedTextView[@text='" + date + "']")).isDisplayed();
            MobileElement mobileElement = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + month + " " + year + "']/following-sibling::\t\n" +
                    "ni.a//following-sibling::ni.b/child::android.widget.CheckedTextView[@text='" + date + "']"));
            mobileElement.click();
        } else {
            try {
                scrollDown();
                DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + month + " " + year + "']/following-sibling::\t\n" +
                        "ni.a//following-sibling::ni.b/child::android.widget.CheckedTextView[@text='" + date + "']")).isDisplayed();
                MobileElement mobileElement = (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + month + " " + year + "']/following-sibling::\t\n" +
                        "ni.a//following-sibling::ni.b/child::android.widget.CheckedTextView[@text='" + date + "']"));
                mobileElement.click();
            } catch (ElementNotVisibleException envf) {
                Reporter.log("Element is not visible even after scrolling");
            }
        }
    }

}

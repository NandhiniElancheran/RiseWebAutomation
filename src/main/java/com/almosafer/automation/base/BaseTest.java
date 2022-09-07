package com.almosafer.automation.base;

import com.almosafer.automation.utils.JsonParser;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.json.JSONObject;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
    public static JSONObject flightDetails;
    private static AppiumDriverLocalService server;

    @BeforeSuite
    public void beforeSuite() {

        server = getAppiumServerDefault();
        if (!server.isRunning()) {
            server.start();
            System.out.println("Server started");
            server.clearOutPutStreams();
        } else
            System.out.println("Appium Server is running");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        DriverManager.initializeDriver();
        flightDetails = JsonParser.parse("flightData.json");
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        ((InteractsWithApps) DriverManager.getDriver()).launchApp();
        System.out.println("\n************** Starting Test: " + m.getName() + " *****************\n");
    }

    @AfterMethod
    public void afterMethod() {
        ((InteractsWithApps) DriverManager.getDriver()).closeApp();
    }

    @AfterTest(alwaysRun = true)
    public void quit() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        if (server.isRunning()) {
            server.stop();
            System.out.println("Appium server started");
        }
    }

}

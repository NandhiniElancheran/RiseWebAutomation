package com.rise.automation.base;

import com.rise.automation.utils.JsonParser;
import org.json.JSONObject;
import org.testng.annotations.*;

public class BaseTest {
    public static JSONObject userDetails;

    @BeforeMethod
    public void beforeTest() throws Exception {
        DriverManager.initializeDriver();
        userDetails = JsonParser.parse("testdata.json");
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().close();
            DriverManager.getDriver().quit();
        }
    }


}

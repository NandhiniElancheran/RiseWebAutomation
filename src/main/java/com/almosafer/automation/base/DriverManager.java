package com.almosafer.automation.base;


import com.almosafer.automation.utils.JsonParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<Properties> properties = new ThreadLocal<Properties>();
    private static String CONFIG_PROPERTY = "config.properties";

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver setDriver) {
        driver.set(setDriver);
    }

    /**
     * Initialising the driver.
     * */
    public static void initializeDriver() throws Exception {

        AppiumDriver driver;
        Properties properties = new Properties();
        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTY);
        properties.load(inputStream);
        setProps(properties);
        JSONObject deviceObj = new JSONObject(JsonParser.parse("Devices.json").getJSONObject("devices").toString());

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceObj.getString("platform"));
        caps.setCapability("deviceName", deviceObj.getString("deviceName"));
        caps.setCapability("appPackage", deviceObj.getString("appPackage"));
        caps.setCapability("appActivity", deviceObj.getString("appActivity"));
        caps.setCapability("udid", deviceObj.getString("udid"));
        caps.setCapability("app", System.getProperty("user.dir") + deviceObj.getString("app_url"));

        URL url = new URL(properties.getProperty("appiumURL"));
        driver = new AndroidDriver(url, caps);
        setDriver(driver);
    }

    public static void setProps(Properties setProps) {
        properties.set(setProps);
    }
}

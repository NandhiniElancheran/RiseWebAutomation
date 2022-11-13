package com.rise.automation.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<Properties> properties = new ThreadLocal<Properties>();
    private static String CONFIG_PROPERTY = "config.properties";

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver setDriver) {
        driver.set(setDriver);
    }

    /**
     * Initialising the driver.
     * */
    public static void initializeDriver() throws Exception {

        WebDriver driver;
        Properties properties = new Properties();
        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTY);
        properties.load(inputStream);
        setProps(properties);

        String url = properties.getProperty("applicationURL");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setDriver(driver);
        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        getDriver().manage().window().maximize();
    }

    public static void setProps(Properties setProps) {
        properties.set(setProps);
    }
}

package com.almosafer.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    final static String filePath = "ExtentReport.html";
    static ExtentReports extentReports;
    static Map extentTestMap = new HashMap();

    public synchronized static ExtentReports getReporter() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
            extentReports = new ExtentReports();
            sparkReporter.config().setDocumentTitle("Almosafer Native App Automation");
            sparkReporter.config().setReportName("Almosafer Automation Test Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
        }
        return extentReports;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}

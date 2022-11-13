package com.rise.automation.listeners.retry_mechanism;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry implements IRetryAnalyzer {
    private static int retryCount = 0;

    public boolean retry(ITestResult result) {
        int maxRetryCount=0;
        if(System.getProperty("maxRetryCount")!=null)
            maxRetryCount= Integer.parseInt(System.getProperty("maxRetryCount"));
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}

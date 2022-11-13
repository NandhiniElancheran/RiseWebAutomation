package com.rise.automation;

import com.rise.automation.base.BaseTest;
import com.rise.automation.elementRepositories.LoginPage;
import com.rise.automation.elementRepositories.ProductsPage;
import org.testng.annotations.Test;


import java.util.logging.Logger;

import static com.rise.automation.utils.JsonParser.*;

public class LoginValidationWithAddToCartTest extends BaseTest {
    public static final Logger logger=Logger.getLogger(LoginValidationWithAddToCartTest.class.getName());

    @Test (description = "Using valid user select three random items")
    public void selectRandomThreeItemsForValidUser() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        String userValid = parseJsonObjToStr(userDetails,"userLogin","validUser");
        String password = parseJsonObjToStr(userDetails,"userLogin","password");
        String noOfProduct = parseJsonObjToStr(userDetails,"userLogin","noOfProduct");

        logger.info("Scenario 1: Verify the title");
        loginPage.verifyPageTitle();

        logger.info("Scenario 2: Enter user details");
        loginPage.enterUserDetails(userValid, password);

        logger.info("Scenario 3: Click on Login button");
        loginPage.clickLoginButton();

        logger.info("Scenario 4: Select the product as per the input");
        productsPage.selectProduct(noOfProduct);

    }

    @Test (description = "Validate locked user error message")
    public void validateLockedUserError() {
        LoginPage loginPage = new LoginPage();

        String lockedUser = parseJsonObjToStr(userDetails,"userLogin","lockedUser");
        String password = parseJsonObjToStr(userDetails,"userLogin","password");

        logger.info("Scenario 1: Verify the title");
        loginPage.verifyPageTitle();

        logger.info("Scenario 2: Enter user details");
        loginPage.enterUserDetails(lockedUser, password);

        logger.info("Scenario 3: Click on Login button");
        loginPage.clickLoginButton();

        logger.info("Scenario 4: Validate error message");
        loginPage.validateErrorMessage();

    }

    @Test (description = "Validate problem user ")
    public void validateProblemUser() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        String problemUser = parseJsonObjToStr(userDetails,"userLogin","problemUser");
        String password = parseJsonObjToStr(userDetails,"userLogin","password");
        String noOfProduct = parseJsonObjToStr(userDetails,"userLogin","noOfProduct");

        logger.info("Scenario 1: Verify the title");
        loginPage.verifyPageTitle();

        logger.info("Scenario 2: Enter user details");
        loginPage.enterUserDetails(problemUser, password);

        logger.info("Scenario 3: Click on Login button");
        loginPage.clickLoginButton();

        logger.info("Scenario 4: Select the product as per the input");
        productsPage.selectProduct(noOfProduct);

    }

    @Test (description = "Validate performance glitch user ")
    public void validatePerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        String performanceGlitchUser = parseJsonObjToStr(userDetails,"userLogin","performanceGlitchUser");
        String password = parseJsonObjToStr(userDetails,"userLogin","password");
        String noOfProduct = parseJsonObjToStr(userDetails,"userLogin","noOfProduct");

        logger.info("Scenario 1: Verify the title");
        loginPage.verifyPageTitle();

        logger.info("Scenario 2: Enter user details");
        loginPage.enterUserDetails(performanceGlitchUser, password);

        logger.info("Scenario 3: Click on Login button");
        loginPage.clickLoginButton();

        logger.info("Scenario 4: Select the product as per the input");
        productsPage.selectProduct(noOfProduct);

    }
}

package com.rise.automation.elementRepositories;

import com.rise.automation.base.BaseCommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * @author Nandhini
 */
public class LoginPage extends BaseCommonActions {

    @FindBy(id = "user-name")
    private WebElement username_input;
    @FindBy (id = "password")
    private WebElement password_input;
    @FindBy (id = "login-button")
    private WebElement login_Btn;
    @FindBy (xpath = "//h3[@data-test='error']")
    private WebElement error_msg;


    /**
     * Description: Verify Welcome screen title
     */
    public void verifyPageTitle(){
        Assert.assertEquals(getPageTitle("Page Title: "),"Swag Labs","Login Page Title is not matched");
    }
    public void enterUserDetails(String username, String password){
        clear(username_input, "Clear input values");
        sendKeys(username_input, username, "Enter the user name");
        clear(password_input, "Clear input values");
        sendKeys(password_input, password, "Enter the password");
    }
    public void clickLoginButton(){
        click(login_Btn, "Click on Login button");
    }
    public  void validateErrorMessage(){
        Assert.assertEquals(error_msg.getText().trim(), "Epic sadface: Sorry, this user has been locked out.", "Error message is not matching");
    }
}

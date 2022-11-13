package com.rise.automation.elementRepositories;

import com.rise.automation.base.BaseCommonActions;
import com.rise.automation.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * @author Nandhini
 */
public class ProductsPage extends BaseCommonActions {

    @FindBy(xpath = "//button[text()='Add to cart']")
    private WebElement AddToCart_btn;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburger_menu;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logout_btn;

    /**
     * Description: Verify and select product
     */
    public void selectProduct(String noOfProduct){
        int sizeOfAddToCartBtn = DriverManager.getDriver().findElements(By.xpath("//button[text()='Add to cart']")).size();
        if(sizeOfAddToCartBtn > 0){
            for(int i=1; i<=Integer.parseInt(noOfProduct); i++){
                DriverManager.getDriver().findElement(By.xpath("(//button[text()='Add to cart'])["+i+"]")).click();
            }
        }
    }

    public void clickHamburgerMenu(){
        click(hamburger_menu, "Click on Hamburger menu");
    }

    public void clickLogout(){
        Assert.assertEquals(getText(logout_btn,"Button text").toLowerCase().trim(),"logout", "Button text not matched");
        click(logout_btn, "Click on Logout");
    }
}

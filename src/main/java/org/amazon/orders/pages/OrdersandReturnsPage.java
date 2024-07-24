package org.amazon.orders.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersandReturnsPage {
    static WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),\"View or edit order\")]")
    WebElement viewEditOrderButton;

    public OrdersandReturnsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickViewEditOrderOption(){
        viewEditOrderButton.click();
    }

    public void highlightViewEditOrderOption(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",viewEditOrderButton);
    }

    public boolean verifyViewEditOrderOption(){
        return viewEditOrderButton.isDisplayed();
    }
}

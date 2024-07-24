package org.amazon.orders.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class OrderDetailsPage {
    static WebDriver driver;

    @FindBy(xpath = "//a[@id=\"Cancel-items_1\" and contains(text(),\"Cancel items\")]")
    WebElement cancelItemsButton;

    public OrderDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyCancelItemsButton(){
        return cancelItemsButton.isDisplayed();
    }

    public void highlightCancelItemsButton(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",cancelItemsButton);
    }

    public void clickCancelItemsButton(){
        cancelItemsButton.click();
    }
}

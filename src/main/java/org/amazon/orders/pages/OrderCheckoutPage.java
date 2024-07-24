package org.amazon.orders.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class OrderCheckoutPage {
    static WebDriver driver;

    @FindAll(@FindBy(id = "placeOrder"))
    List<WebElement> placeOrderButtonsList;

    @FindAll(@FindBy(xpath = "//input[@type=\"radio\"]"))
    List<WebElement> chooseDeliveryOptionList;

    public OrderCheckoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyPlaceOrderButton(){
        return placeOrderButtonsList.isEmpty();
    }

    public boolean verifyChooseDeliverOptions(){
        return chooseDeliveryOptionList.isEmpty();
    }

    public void chooseDeliverOption() throws InterruptedException {
        Random ran = new Random();
        int num = ran.nextInt(0,2);
        WebElement option = chooseDeliveryOptionList.get(num);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        option.click();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", option);
        Thread.sleep(1000);
    }

    public void clickPlaceOrderButton() {
        Random ran = new Random();
        int num = ran.nextInt(0, 1);
        placeOrderButtonsList.get(num).click();
    }
}

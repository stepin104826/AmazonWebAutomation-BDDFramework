package org.amazon.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class ShoppingCartPage {
    static WebDriver driver;

    @FindAll(@FindBy(xpath = "//div[@data-name=\"Active Items\"]"))
    List<WebElement> cartProducts;

    @FindBy(xpath = "//input[@value=\"Delete\"]")
    WebElement deleteProductFromCartOption;

    @FindAll(@FindBy(xpath = "//input[@name=\"submit.addToCart\"]"))
    List<WebElement> addToCartButtonList;

    @FindBy(xpath = "//input[@name=\"proceedToRetailCheckout\"]")
    WebElement proceedToCheckoutButton;

    static List<String> titles;


    public ShoppingCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public List<String> getCartProductTitles(){
        titles = new ArrayList();
        for(WebElement title: cartProducts){
            titles.add(title.getText());
        }
        return titles;
    }

    public void highlightSelectedProductTitle() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", titles.get(0));
    }

    public void clickAddToCartButton() throws InterruptedException {
        Random ran = new Random();
        int num = ran.nextInt(addToCartButtonList.size()) ;
        WebElement button = addToCartButtonList.get(num);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",button);
        Thread.sleep(2000);
        button.click();
    }


    public void clickProceedToCheckoutOption(){
        proceedToCheckoutButton.click();
    }

    public void highlightProceedToCheckoutOption() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", proceedToCheckoutButton);
    }

    public boolean checkIfShoppingCartIsEmpty(){
        return this.getCartProductTitles().isEmpty();
    }

    public void clickDeleteProductFromCartOption(){
        deleteProductFromCartOption.click();
    }

    public boolean verifyDeleteProductFromCartOption(){
        return deleteProductFromCartOption.isDisplayed();
    }

    public boolean verifyProceedToCheckoutOption(){
        return proceedToCheckoutButton.isDisplayed();
    }
}

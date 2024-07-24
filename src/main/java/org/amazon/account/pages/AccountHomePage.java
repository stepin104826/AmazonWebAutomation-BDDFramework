package org.amazon.account.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;


public class AccountHomePage {
    static WebDriver driver;


    @FindBy(xpath = "//a[@id=\"nav-link-accountList\"]")
    WebElement accountsAndListsOption;

    @FindBy(xpath = "//a[@id=\"nav-orders\"]")
    WebElement returnsAndOrdersOption;

    @FindBy(xpath = "//input[@id=\"twotabsearchtextbox\"]")
    WebElement productSearchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchIconButton;

    @FindBy(id = "nav-cart")
    WebElement shoppingCartButton;

    public AccountHomePage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    public void clickSignIn(){
        accountsAndListsOption.click();
    }

    public void highlightSignInOption() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", accountsAndListsOption);
    }


    public void performSearch(String input) throws InterruptedException {
        productSearchBox.sendKeys(input);
        searchIconButton.click();
    }


    public void highlightSearchBox() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", productSearchBox);
    }

    public void clickShoppingCartOption(){
        shoppingCartButton.click();
    }

    public void highlightShoppingCartOption() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", shoppingCartButton);
    }

    public void clickReturnsAndOrdersOption(){
        returnsAndOrdersOption.click();
    }

    public void highlightReturnsAndOrdersOption() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", returnsAndOrdersOption);
    }

    public boolean verifySearchBox(){
        return productSearchBox.isDisplayed();
    }

    public boolean verifySearchIconButton(){
        return searchIconButton.isDisplayed();
    }

    public boolean verifyShoppingCartOption(){
        return shoppingCartButton.isDisplayed();
    }

    public boolean verifyReturnsAndOrdersOption(){
        return returnsAndOrdersOption.isDisplayed();
    }
}

package org.amazon.products.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ProductResultsPage {
    static WebDriver driver;

    @FindBy(xpath = "//h2[contains(text(),\"Res\")]")
    WebElement resultsHeading;

    @FindAll(@FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]"))
    List<WebElement> searchResults;

    @FindBy(xpath = "//span[@id=\"a-autoid-16-announce\"]")
    WebElement qtyDropdown;

    @FindBy(name= "submit.add-to-cart")
    WebElement addToCartButton;

    @FindBy(xpath = "//span[@id=\"productTitle\"]")
    WebElement selectedProductTitle;

    static WebElement product;


    public ProductResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyResultsTitle(){
        return resultsHeading.isDisplayed();
    }

    public boolean verifyQtyDropdown(){
        return qtyDropdown.isDisplayed();
    }

    public String getAddToCartButtonText(String text){
        return addToCartButton.getText();
    }

    public boolean verifyAddToCartButton(){
        return addToCartButton.isDisplayed();
    }

    public int getProductsResultsListSize(){
        return searchResults.size();
    }

    public void selectProduct() throws InterruptedException {
        Random ran = new Random();
        int num = ran.nextInt(4) + 2;
        String xPath = String.format("//div[@data-cel-widget=\"search_result_%d\"]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]",num);
        product = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",product);
    }

    public void clickSelectedProduct(){
        product.click();
    }

    public void highlightSelectedProduct() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", product);
    }

    public void selectProductQty(){
        qtyDropdown.click();
        Random ran = new Random();
        int qty = ran.nextInt(1) + 10;
        String pathExpression = String.format("//a[@id=\"quantity_%d\"]",qty);
        WebElement quantity = driver.findElement(By.xpath(pathExpression));
        quantity.click();
    }

    public void clickAddToCart(){
        addToCartButton.click();
    }

    public void highlightAddToCart(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", addToCartButton);
    }

    public String getSelectedProductTitle(){
        return selectedProductTitle.getText();
    }

}

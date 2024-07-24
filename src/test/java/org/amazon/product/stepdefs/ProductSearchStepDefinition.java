package org.amazon.product.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.amazon.account.pages.AccountHomePage;
import org.amazon.account.pages.AccountSignInPage;
import org.amazon.products.pages.ProductResultsPage;
import org.amazon.cart.pages.ShoppingCartPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class ProductSearchStepDefinition {
     private WebDriver driver;
     private AccountSignInPage asp;
     private AccountHomePage ahp;
     private ProductResultsPage prp;
     private ShoppingCartPage scp;
     String productTitle;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        ahp = new AccountHomePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Given("I am on the Amazon Homepage")
    public void i_am_on_the_amazon_homepage() throws InterruptedException {
        ahp.highlightSignInOption();
        Thread.sleep(1000);
        ahp.clickSignIn();

        asp = new AccountSignInPage(driver);

        asp.highlightEmailBox();
        Thread.sleep(1000);
        asp.enterEmail("dhaksiny@buffalo.edu");
        asp.clickonContinue();
        asp.highlightPasswordBox();
        Thread.sleep(1000);
        asp.enterPassword("Dhaksu@555");

        asp.clickSignin();
        Thread.sleep(10000);
        Assert.assertEquals("Amazon.com. Spend less. Smile more.",driver.getTitle(),"Wrong Home page title!");
    }

    @Given("I have entered the product search phrase {string} and have clicked the search icon")
    public void i_have_entered_the_product_search_phrase_and_have_clicked_the_search_icon(String phrase) throws InterruptedException {
        Assert.assertTrue(ahp.verifySearchBox() && ahp.verifySearchIconButton(),"Search box and search icon button not displayed!");
        ahp.highlightSearchBox();
        Thread.sleep(1000);
        ahp.performSearch(phrase);
        Thread.sleep(2000);
    }

    @Then("I should be shown relevant products as results")
    public void i_should_be_shown_relevant_products_as_results() {
        prp = new ProductResultsPage(driver);
        Assert.assertTrue(prp.verifyResultsTitle());
        Assert.assertTrue(prp.getProductsResultsListSize()!=0, "Results list is null!");
    }

    @And("I have selected a product")
    public void i_have_selected_a_product() throws InterruptedException {
        prp.selectProduct();
        prp.highlightSelectedProduct();
        Thread.sleep(1000);
        prp.clickSelectedProduct();
    }

    @When("{string} is clicked")
    public void is_clicked(String inp) throws InterruptedException {
        Assert.assertTrue(prp.verifyAddToCartButton());
        prp.highlightAddToCart();
        Thread.sleep(1000);
        prp.clickAddToCart();
        Thread.sleep(1000);
    }

    @Then("the correct product is added to the cart")
    public void the_product_with_the_right_quantity_is_added_to_the_cart() throws InterruptedException {
        scp = new ShoppingCartPage(driver);
        Assert.assertTrue(ahp.verifyShoppingCartOption(),"Shopping Cart Option is not displayed!");
        ahp.highlightShoppingCartOption();
        Thread.sleep(1000);
        ahp.clickShoppingCartOption();
        Thread.sleep(2000);
        Assert.assertEquals("Amazon.com Shopping Cart",driver.getTitle(),"Shopping Cart Page Title is not correct!");
        ((JavascriptExecutor)driver).executeScript("scrollBy(0,200);");
        Thread.sleep(2000);
    }

}

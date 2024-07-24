package org.amazon.e2e.stepdefs;

import BrowserUtilities.WebDrivermanager;
import com.google.common.base.Verify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.amazon.account.pages.AccountHomePage;
import org.amazon.account.pages.AccountSignInPage;
import org.amazon.cart.pages.ShoppingCartPage;
import org.amazon.orders.pages.OrderCheckoutPage;
import org.amazon.orders.pages.OrderDetailsPage;
import org.amazon.orders.pages.OrdersandReturnsPage;
import org.amazon.products.pages.ProductResultsPage;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class EndToEndFlowStepDefinition {

    WebDriver driver;
    private AccountSignInPage asp;
    private AccountHomePage ahp;
    private ProductResultsPage prp;
    private ShoppingCartPage scp;
    private OrderCheckoutPage ocp;
    private OrdersandReturnsPage orp;
    private OrderDetailsPage odp;
    String productTitle;
    static WebElement alertHeading;

    @Before
    public void setUp(){
        driver = WebDrivermanager.getDriver("firefox");
        driver.get("https://www.amazon.com/");
        ahp = new AccountHomePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() throws InterruptedException {
        if(driver != null) {
            driver.manage().window().minimize();
            Thread.sleep(1000);
            driver.quit();
        }
    }

    @Given("I am on the Amazon Login Page")
    public void i_am_on_the_amazon_login_page() throws InterruptedException {
        ahp.highlightSignInOption();
        Thread.sleep(1000);
        ahp.clickSignIn();
        asp = new AccountSignInPage(driver);
        Thread.sleep(2000);
    }

    @When("I provide a valid {string}, click on continue")
    public void i_provide_a_valid_click_on_continue(String email) throws InterruptedException {
        asp.highlightEmailBox();
        Thread.sleep(1000);
        asp.enterEmail(email);
        asp.highlightContinueButton();
        Thread.sleep(1000);
        asp.clickonContinue();
        Thread.sleep(2000);
    }

    @When("provide a valid {string}, click on sign-in")
    public void provide_a_valid_click_on_sign_in(String pass) throws InterruptedException {
        asp.highlightPasswordBox();
        Thread.sleep(1000);
        asp.enterPassword(pass);
        asp.highlightSignIn();
        Thread.sleep(1000);
        asp.clickSignin();
        Thread.sleep(5000);
    }

    @When("I search for {string} in the product search")
    public void i_search_for_sport_shoes_in_the_product_search(String phrase) throws InterruptedException {
        ahp.highlightSearchBox();
        Thread.sleep(1000);
        ahp.performSearch(phrase);
        Thread.sleep(2000);
    }

    @When("I select a product from the search results and add to the cart")
    public void i_select_a_product_from_the_search_results_and_add_to_the_cart() throws InterruptedException {
        prp = new ProductResultsPage(driver);
        prp.selectProduct();
        Thread.sleep(2000);
        prp.highlightSelectedProduct();
        Thread.sleep(1000);
        prp.clickSelectedProduct();
        Thread.sleep(2000);
        prp.highlightAddToCart();
        Thread.sleep(1000);
        prp.clickAddToCart();
        Thread.sleep(2000);
    }

    @Then("I should see the product added in the cart")
    public void i_should_see_the_product_added_in_the_cart() throws InterruptedException {
        scp = new ShoppingCartPage(driver);
        ahp.highlightShoppingCartOption();
        Thread.sleep(1000);
        ahp.clickShoppingCartOption();
        Thread.sleep(1000);
        Verify.verify(!scp.checkIfShoppingCartIsEmpty(),"Shopping cart is empty!");
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() throws InterruptedException {
        scp.highlightProceedToCheckoutOption();
        Thread.sleep(1000);
        scp.clickProceedToCheckoutOption();
        Thread.sleep(2000);
        WebElement continueCheckoutButton = null;
        boolean present = true;
        try{
            continueCheckoutButton = driver.findElement(By.xpath("//a[contains(text(),\"Continue to checkout\")]"));
        }
        catch(Exception e){
            present = false;
        }
        finally {
            if (present) {
                continueCheckoutButton.click();
            }
            else {
                Thread.sleep(2000);
                ocp = new OrderCheckoutPage(driver);
                Assert.assertEquals("Place Your Order - Amazon Checkout", driver.getTitle(), "Wrong checkout page title");
                Assert.assertFalse(ocp.verifyPlaceOrderButton(), "Place Order button not displayed!");
            }
        }
    }

    @When("I select delivery date and time")
    public void i_select_delivery_date_and_time() throws InterruptedException {
        Assert.assertFalse(ocp.verifyChooseDeliverOptions(),"Choose Delivery Options not displayed!");
        ocp.chooseDeliverOption();
        Thread.sleep(2000);
    }

    @When("I place the order")
    public void i_place_the_order() throws InterruptedException {
        ocp.clickPlaceOrderButton();
        Thread.sleep(10000);
        alertHeading = driver.findElement(By.xpath("//h4[@class=\"a-alert-heading\"]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",alertHeading);
        Thread.sleep(1000);

    }

    @Then("my order shd be successfully placed")
    public void my_order_shd_be_successfully_placed() throws InterruptedException {
        Assert.assertEquals("Order placed, thanks!",alertHeading.getText(),"Success Message not displayed!");
    }

    @When("I navigate to the order history page")
    public void i_navigate_to_the_order_history_page() throws InterruptedException {
        ahp.highlightReturnsAndOrdersOption();;
        Thread.sleep(1000);
        ahp.clickReturnsAndOrdersOption();
        Thread.sleep(2000);
    }

    @When("I cancel the placed order")
    public void i_cancel_the_placed_order() throws InterruptedException {
        orp = new OrdersandReturnsPage(driver);
        orp.highlightViewEditOrderOption();
        Thread.sleep(1000);
        orp.clickViewEditOrderOption();
        Thread.sleep(2000);

        odp = new OrderDetailsPage(driver);
        Assert.assertEquals("Order Details",driver.getTitle(),"Wrong page title!");
        Assert.assertTrue(odp.verifyCancelItemsButton());
    }

    @Then("my order should be successfully cancelled")
    public void my_order_shd_be_successfully_cancelled() throws InterruptedException {
        odp.clickCancelItemsButton();
        Thread.sleep(2000);

        WebElement cancelOrderButton = driver.findElement(By.xpath("//input[@type=\"submit\" and @name=\"cq.submit\"]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",cancelOrderButton);
        Thread.sleep(1000);
        cancelOrderButton.click();
        Thread.sleep(2000);

        WebElement alertHeading = driver.findElement(By.xpath("//h4[@class=\"a-alert-heading\"]"));
        String alertMsg = alertHeading.getText();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",alertHeading);
        Thread.sleep(2000);
        Assert.assertEquals("This order has been cancelled.",alertMsg,"Order cancellation message not displayed!");
    }


}

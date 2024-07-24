package org.amazon.order.stepdefs;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.amazon.account.pages.AccountHomePage;
import org.amazon.account.pages.AccountSignInPage;
import org.amazon.cart.pages.ShoppingCartPage;
import org.amazon.orders.pages.OrderCheckoutPage;
import org.amazon.orders.pages.OrderDetailsPage;
import org.amazon.orders.pages.OrdersandReturnsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class OrderPlaceStepDefinition {
    private WebDriver driver;
    private AccountSignInPage asp;
    private AccountHomePage ahp;
    private ShoppingCartPage scp;
    private OrderCheckoutPage ocp;
    private OrdersandReturnsPage orp;
    private OrderDetailsPage odp;


    @Given("I am on the Amazon home page with an item in the cart")
    public void i_am_on_the_order_checkout_page() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        ahp = new AccountHomePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ahp.clickSignIn();

        asp = new AccountSignInPage(driver);

        asp.enterEmail("dhaksiny@buffalo.edu");
        asp.clickonContinue();
        asp.enterPassword("Dhaksu@555");

        asp.clickSignin();
        Thread.sleep(2000);
        Assert.assertEquals("Amazon.com. Spend less. Smile more.", driver.getTitle(), "Wrong Home page title!");
        Thread.sleep(2000);
    }


    @When("I navigate to the order checkout page")
    public void navigate_to_checkout_page() throws InterruptedException {

        ahp.clickShoppingCartOption();
        scp = new ShoppingCartPage(driver);

        Assert.assertFalse(scp.checkIfShoppingCartIsEmpty(),"Shopping cart is Empty! Add an item!");

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
            if(present) {
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

    @When("I select the delivery date and time")
    public void i_select_delivery_date_and_time() throws InterruptedException {
        Assert.assertFalse(ocp.verifyChooseDeliverOptions(),"Choose Delivery Options not displayed!");
        ocp.chooseDeliverOption();
        Thread.sleep(2000);
    }

    @When("I click on place the order option")
    public void i_click_on_place_the_order_option() throws InterruptedException {
        ocp.clickPlaceOrderButton();
        Thread.sleep(2000);
    }

    @Then("my order should be successfully placed")
    public void my_order_should_be_successfully_placed() throws InterruptedException {
        Assert.assertEquals("Order placed, thanks!",driver.findElement(By.xpath("//h4[@class=\"a-alert-heading\"]")).getText(),"Success Message not displayed!");
    }

    @Given("I have placed an order successfully and on the order history page")
    public void i_have_placed_an_order_successfully_and_on_the_order_history_page() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(ahp.verifyReturnsAndOrdersOption(),"Orders and Returns Option not displayed!");
        ahp.clickReturnsAndOrdersOption();
        Thread.sleep(2000);

        orp = new OrdersandReturnsPage(driver);
        Assert.assertEquals("Your Orders",driver.getTitle(),"Wrong page title!");
        Assert.assertTrue(orp.verifyViewEditOrderOption(), "View or Edit option is not displayed!");
        Thread.sleep(2000);
    }

    @When("I click on Cancel Placed Items")
    public void i_click_on_cancel_placed_items() throws InterruptedException {
        orp.clickViewEditOrderOption();
        Thread.sleep(2000);

        odp = new OrderDetailsPage(driver);
        Assert.assertEquals("Order Details",driver.getTitle(),"Wrong page title!");
        Assert.assertTrue(odp.verifyCancelItemsButton());
    }

    @Then("my order items should successfully get cancelled")
    public void my_order_items_should_successfully_get_cancelled() throws InterruptedException {
        odp.clickCancelItemsButton();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@type=\"submit\" and @name=\"cq.submit\"]")).click();

        String alertMsg = driver.findElement(By.xpath("//h4[@class=\"a-alert-heading\"]")).getText();
        Assert.assertEquals("This order has been cancelled.",alertMsg,"Order cancellation message not displayed!");
    }
}

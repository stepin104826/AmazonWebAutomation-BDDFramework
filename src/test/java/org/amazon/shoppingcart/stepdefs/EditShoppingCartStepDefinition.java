package org.amazon.shoppingcart.stepdefs;

import com.google.common.base.Verify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.amazon.account.pages.AccountHomePage;
import org.amazon.account.pages.AccountSignInPage;
import org.amazon.cart.pages.ShoppingCartPage;
import org.amazon.products.pages.ProductResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class EditShoppingCartStepDefinition {
     private WebDriver driver;
     private AccountSignInPage asp;
     private AccountHomePage ahp;
     private ShoppingCartPage scp;
     private ProductResultsPage prp;
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

    @Given("I am on the Shopping Cart page with some product\\(s) in the cart")
    public void i_am_on_the_shopping_cart_page() throws InterruptedException {
        ahp.clickSignIn();

        asp = new AccountSignInPage(driver);

        asp.enterEmail("dhaksiny@buffalo.edu");
        asp.clickonContinue();
        asp.enterPassword("Dhaksu@555");

        asp.clickSignin();
        Thread.sleep(10000);
        Assert.assertEquals("Amazon.com. Spend less. Smile more.",driver.getTitle(),"Wrong Home page title!");
        ahp.performSearch("Shoes");
        Thread.sleep(2000);

        prp = new ProductResultsPage(driver);
        prp.selectProduct();
        prp.clickSelectedProduct();
        Thread.sleep(2000);
        prp.clickAddToCart();
        Thread.sleep(2000);
        ahp.clickShoppingCartOption();
     }

    @When("I click {string} option, the added product\\(s) should be deleted")
    public void i_click_option_the_added_product_s_should_be_deleted(String string) throws InterruptedException {
        scp = new ShoppingCartPage(driver);
        Assert.assertTrue(scp.verifyDeleteProductFromCartOption());
        Thread.sleep(2000);
        scp.clickDeleteProductFromCartOption();
        Thread.sleep(2000);
    }
    @When("I click {string} from the recently viewed products, the product\\(s) should be added")
    public void i_click_from_the_recently_viewed_products_the_product_s_should_be_added(String string) throws InterruptedException {
        scp.clickAddToCartButton();
        ahp.clickShoppingCartOption();
        Assert.assertFalse(scp.checkIfShoppingCartIsEmpty(),"Shopping cart is Empty!");
    }


}

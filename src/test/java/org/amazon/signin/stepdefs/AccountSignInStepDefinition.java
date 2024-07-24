package org.amazon.signin.stepdefs;

import BrowserUtilities.WebDrivermanager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.amazon.account.pages.AccountSignInPage;
import org.amazon.account.pages.AccountHomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AccountSignInStepDefinition {

    private WebDriver driver;
    private AccountSignInPage asp;
    private AccountHomePage ahp;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }


    @Given("I am on the Amazon login page")
    public void i_am_on_the_amazon_login_page() throws InterruptedException {
        ahp = new AccountHomePage(driver);
        ahp.highlightSignInOption();
        Thread.sleep(1000);
        ahp.clickSignIn();
        asp = new AccountSignInPage(driver);
        Thread.sleep(2000);
    }

    @Given("I have entered a valid email")
    public void i_have_entered_a_valid_email(DataTable email) throws InterruptedException {
        Assert.assertTrue(asp.checkEmailTextbox(),"Email Text box not displayed!");
        List<String> data = email.asList();
        for(int i = 0; i < data.size(); i++) {
            asp.enterEmail(data.get(i));
        }
        Thread.sleep(2000);
    }

    @And("clicked on continue")
    public void clicked_on_continue() throws InterruptedException {
        asp.highlightContinueButton();
        Thread.sleep(1000);
        asp.clickonContinue();
        Thread.sleep(2000);
    }

    @And("I have entered a valid password")
    public void i_have_entered_a_valid_password(DataTable password) throws InterruptedException {
        Assert.assertTrue(asp.checkPwdTextBox(),"Password box not displayed!");
        List<String> data = password.asList();
        for(int i = 0; i < data.size(); i++) {
            asp.enterPassword(data.get(i));
        }
        Thread.sleep(2000);
    }

    @When("I click on the sign-in button")
    public void i_click_on_signin_button() throws InterruptedException {
        Assert.assertTrue(asp.checkLoginButton(),"Sign-in Button not displayed!");
        asp.highlightSignIn();
        Thread.sleep(1000);
        asp.clickSignin();
        Thread.sleep(15000);
    }

    @Then("I should be signed in to my account successfully")
    public void i_should_be_signedin_successfully() {
        Assert.assertEquals("Amazon.com. Spend less. Smile more.",driver.getTitle(),"Wrong home page!");
    }

    @Given("I have entered invalid {string}")
    public void i_have_entered_invalid_email_n_password(String email) throws InterruptedException {
        asp.highlightEmailBox();
        Thread.sleep(1000);
        asp.enterEmail(email);
        Thread.sleep(2000);
        asp.highlightContinueButton();
        Thread.sleep(1000);
        asp.clickonContinue();
        Thread.sleep(2000);
    }

    @Then("I should see an error message indicating {string}")
    public void i_should_see_an_error_message_indicating(String message) {
        if(message.equals("")) {
            Assert.assertTrue(asp.checkMissingAlert(), "Alert message not displayed!");
            String alertText = asp.getMissingAlertMsgText();
            Assert.assertEquals(message, alertText);
        }
        else{
            Assert.assertTrue(asp.checkInvalidAlert(), "Alert message not displayed!");
            String alertText = asp.getInvalidAlertMsgText();
            Assert.assertEquals(message, alertText);
        }
    }

    @When("I click on the {string} link")
    public void i_click_on_the_link(String string) throws InterruptedException {
        Assert.assertTrue(asp.checkForgottenPwdLink(),"Password reset link not displayed!");
        asp.highlightForgottenPwdLink();
        Thread.sleep(1000);
        asp.clickForgottenPwdLink();
        Thread.sleep(2000);
    }

    @Then("I should be redirected to the password reset page")
    public void i_should_be_redirected_to_the_password_reset_page() {
        Assert.assertEquals("Amazon Password Assistance",driver.getTitle(),"Not redirected to the password redirect link!");
    }

}

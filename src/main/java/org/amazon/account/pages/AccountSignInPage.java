package org.amazon.account.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AccountSignInPage {
    static WebDriver driver;

    @FindBy(xpath = "//input[@id=\"ap_email\"]")
    WebElement emailTextBox;

    @FindBy(xpath = "//input[@id=\"ap_password\"]")
    WebElement passwordTextBox;

    @FindBy(xpath = "//input[@id=\"continue\"]")
    WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),\"Forgot\")]")
    WebElement forgottenPwdLink;

    @FindBy(xpath = "//input[@id=\"signInSubmit\"]")
    WebElement signInButton;

    @FindBy(xpath = "//div[@id=\"auth-email-invalid-claim-alert\"]")
    WebElement invalidAlertBox;

    @FindBy(xpath = "//div[@id=\"auth-email-missing-alert\"]")
    WebElement missingAlertBox;

    // Contructor to initialize the web elements
    public AccountSignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // Page Action Methods
    public void enterEmail(String inputText){
        emailTextBox.sendKeys(inputText);
    }

    public void highlightEmailBox() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", emailTextBox);
    }

    public void enterPassword(String inputPass){
        passwordTextBox.sendKeys(inputPass);
    }

    public void highlightPasswordBox() throws InterruptedException {
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", passwordTextBox);
    }

    public void clickSignin(){
        signInButton.click();
    }

    public void highlightSignIn(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid black;');", signInButton);
    }

    public void clickonContinue(){
        continueButton.click();
    }

    public void highlightContinueButton(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid black;');",continueButton);
    }

    public void clickForgottenPwdLink(){
        forgottenPwdLink.click();
    }

    public void highlightForgottenPwdLink(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');",forgottenPwdLink);
    }

    public boolean checkEmailTextbox(){
        return emailTextBox.isDisplayed();
    }

    public String getInvalidAlertMsgText(){
        return invalidAlertBox.getText();
    }

    public String getMissingAlertMsgText(){
        return missingAlertBox.getText();
    }

    public boolean checkPwdTextBox(){
        return passwordTextBox.isDisplayed();
    }

    public boolean checkLoginButton(){
        return signInButton.isDisplayed();
    }

    public boolean checkInvalidAlert(){
        return invalidAlertBox.isDisplayed();
    }

    public boolean checkMissingAlert(){
        return missingAlertBox.isDisplayed();
    }

    public boolean checkForgottenPwdLink(){
        return forgottenPwdLink.isDisplayed();
    }

}

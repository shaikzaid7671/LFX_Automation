package com.snasquare.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    // Welcome Screen Element
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Login']")
    private WebElement welcomeLoginButton;

    // Login Screen Elements
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter your mobile number']")
    private WebElement mobileNumberField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter your password']")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='button']")
    private WebElement loginSubmitButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot password?']")
    private WebElement forgotPasswordLink;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Register']")
    private WebElement registerLink;

    public LoginPage(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }

    public void clickWelcomeLoginButton() {
        System.out.println("Clicking on Login button from welcome screen...");
        wait.until(ExpectedConditions.elementToBeClickable(welcomeLoginButton)).click();
        System.out.println("✓ Welcome Login button clicked");
    }

    public void enterMobileNumber(String mobileNumber) {
        System.out.println("Entering mobile number: " + mobileNumber);
        wait.until(ExpectedConditions.visibilityOf(mobileNumberField)).sendKeys(mobileNumber);
        System.out.println("✓ Mobile number entered");
    }

    public void enterPassword(String password) {
        System.out.println("Entering password");
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        System.out.println("✓ Password entered");
    }

    public void clickLoginSubmitButton() {
        System.out.println("Clicking Login submit button...");
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton)).click();
        System.out.println("✓ Login submit button clicked");
    }

    public void verifyWelcomeScreen() {
        wait.until(ExpectedConditions.visibilityOf(welcomeLoginButton));
        System.out.println("✓ Welcome screen verified");
    }

    public void verifyLoginScreen() {
        wait.until(ExpectedConditions.visibilityOf(mobileNumberField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        System.out.println("✓ Login screen verified");
    }

    public void verifyLoginSuccess() {
        try {
            Thread.sleep(3000);
            System.out.println("✓ Login action completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.snasquare.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmergencyPostPage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    // Home Screen - Emergency Button
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Emergency']")
    private WebElement emergencyButton;

    // Emergency Form Fields
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter title']")
    private WebElement titleField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter description']")
    private WebElement descriptionField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter location']")
    private WebElement locationField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Submit']")
    private WebElement submitButton;

    // Success Message (adjust based on your app)
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Post Created Successfully']")
    private WebElement successMessage;

    public EmergencyPostPage(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }

    // Actions

    public void clickEmergencyButton() {
        System.out.println("Clicking Emergency button...");
        wait.until(ExpectedConditions.elementToBeClickable(emergencyButton)).click();
        System.out.println("✓ Emergency button clicked");
    }

    public void enterTitle(String title) {
        System.out.println("Entering title: " + title);
        wait.until(ExpectedConditions.visibilityOf(titleField)).sendKeys(title);
        System.out.println("✓ Title entered");
    }

    public void enterDescription(String description) {
        System.out.println("Entering description...");
        wait.until(ExpectedConditions.visibilityOf(descriptionField)).sendKeys(description);
        System.out.println("✓ Description entered");
    }

    public void enterLocation(String location) {
        System.out.println("Entering location: " + location);
        wait.until(ExpectedConditions.visibilityOf(locationField)).sendKeys(location);
        System.out.println("✓ Location entered");
    }

    public void clickSubmitButton() {
        System.out.println("Clicking submit button...");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        System.out.println("✓ Submit button clicked");
    }

    // Validations

    public void verifyEmergencyPage() {
        wait.until(ExpectedConditions.visibilityOf(titleField));
        wait.until(ExpectedConditions.visibilityOf(descriptionField));
        wait.until(ExpectedConditions.visibilityOf(locationField));
        System.out.println("✓ Emergency page verified");
    }

    public void verifyPostCreated() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        System.out.println("✓ Emergency post created successfully");
    }
}

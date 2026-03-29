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

    // ---------------- LOCATORS ---------------- //

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='POST EMERGENCY']")
    private WebElement emergencyButton;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    private WebElement allowLocation;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='󰻜']")
    private WebElement uploadButton;

    @AndroidFindBy(xpath = "(//android.widget.ImageView)[2]")
    private WebElement firstImage;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Add') or contains(@text,'Done')]")
    private WebElement addImageButton;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    private WebElement descriptionField;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    private WebElement informedField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Manual Address']")
    private WebElement manualAddressButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Casualty')]")
    private WebElement casualtyOption;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='\uDB81\uDC8A, Post']")
    private WebElement postButton;

    // ---------------- ACTIONS ---------------- //

    public void openEmergencyPage() {
        wait.until(ExpectedConditions.elementToBeClickable(emergencyButton)).click();
        System.out.println("🚨 Emergency Page Opened");
    }

    public void handleLocationPermission() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(allowLocation)).click();
            System.out.println("✅ Location permission granted");
        } catch (Exception e) {
            System.out.println("⚠️ Permission popup not shown");
        }
    }

    public void uploadImage() {
        System.out.println("📸 Uploading Image...");
        wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(firstImage)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addImageButton)).click();

        System.out.println("✅ Image uploaded");
    }

    public void enterDescription(String text) {
        wait.until(ExpectedConditions.visibilityOf(descriptionField)).sendKeys(text);
    }

    public void enterInformed(String text) {
        wait.until(ExpectedConditions.visibilityOf(informedField)).sendKeys(text);
    }

    public void clickManualAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(manualAddressButton)).click();
    }

    public void selectCasualty() {
        wait.until(ExpectedConditions.elementToBeClickable(casualtyOption)).click();
    }

    public void clickPost() {
        wait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
        System.out.println("✅ Emergency Post Submitted");
    }
}

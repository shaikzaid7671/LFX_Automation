package com.snasquare.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LoginTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "Android Device");
        cap.setCapability("udid", "192.168.29.113:5555");
        cap.setCapability("automationName", "UiAutomator2");

        cap.setCapability("appPackage", "com.android.settings");
        cap.setCapability("appActivity", ".Settings");

        cap.setCapability("noReset", true);
        cap.setCapability("fullReset", false);

        // ✅ FIXED (removed local variable)
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                cap
        );

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void automatedLoginTest() {

        // Click Login button
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Login")
                )
        );
        loginButton.click();

        // Wait for input fields
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.className("android.widget.EditText"), 1));

        List<WebElement> inputFields =
                driver.findElements(By.className("android.widget.EditText"));

        // Enter Phone Number
        inputFields.get(0).sendKeys("7671003479");

        // Enter Password
        inputFields.get(1).sendKeys("Z@1d12345");

        driver.hideKeyboard();

        // Click Login again
        loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Login")
                )
        );
        loginButton.click();

        // Verify login success
        boolean loginFailed;

        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOfElementLocated(
                            By.className("android.widget.EditText")));
            loginFailed = false;
        } catch (Exception e) {
            loginFailed = true;
        }

        Assert.assertFalse(loginFailed,
                "❌ Login Failed");

        System.out.println("✅ Login Successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

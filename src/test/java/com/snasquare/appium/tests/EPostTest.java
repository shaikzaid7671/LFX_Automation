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

public class EPostTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "Android Device");
        cap.setCapability("udid", "192.168.1.10:5555");
        cap.setCapability("automationName", "UiAutomator2");

        cap.setCapability("appPackage", "com.lfx");
        cap.setCapability("appActivity", "com.lfx.MainActivity");

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
    public void LoginTest() {

        /* ---------------- LOGIN ---------------- */

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Login")
                )
        );
        loginButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.className("android.widget.EditText"), 1));

        List<WebElement> fields =
                driver.findElements(By.className("android.widget.EditText"));

        fields.get(0).sendKeys("7671003479");
        fields.get(1).sendKeys("Z@1d12345");

        driver.hideKeyboard();

        loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Login")
                )
        );
        loginButton.click();

        System.out.println("✅ Login Successful");


        /* ---------------- OPEN EMERGENCY PAGE ---------------- */

            WebElement emergencyBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.Button[@content-desc=\"POST EMERGENCY\"]")
                    )
            );
            emergencyBtn.click();

            System.out.println("🚨 Emergency Page Opened");


            // Handle Location Permission

            try {

                WebElement allowLocation = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
                        )
                );

                allowLocation.click();

                System.out.println("✅ Location permission granted (While using the app)");

            } catch (Exception e) {

                System.out.println("⚠️ Permission popup not displayed");

            }

            /* ---------------- UPLOAD IMAGE ---------------- */

            System.out.println("📸 Uploading Image...");

            // Click Upload Image button
            WebElement uploadBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.view.ViewGroup[@content-desc=\"\uDB83\uDEDC\"]")
                    )
            );
            uploadBtn.click();

            // Wait for gallery to open
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Select first image from Recent
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement firstImage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[5]/android.view.View[2]/android.view.View[2]/android.view.View")
            ));

            firstImage.click();
            System.out.println("First image selected");

            // Click ADD / DONE button
            WebElement addButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[6]/android.view.View[3]/android.widget.Button")
                    )
            );
            addButton.click();

            System.out.println("✅ Image uploaded successfully");

            /* ---------------- DESCRIPTION ---------------- */

            WebElement desc = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.EditText[@resource-id=\"text-input-outlined\" and @text=\"Describe the details\"]")
                    )
            );

            desc.sendKeys("Accident on highway road. Need medical help immediately");


            /* ---------------- INFORMED FIELD ---------------- */

            WebElement informed = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.EditText[@resource-id=\"text-input-outlined\" and @text=\"Police station or hospital informed to\"]")
                    )
            );

            informed.sendKeys("Local Hospital");


            /* -------- SCROLL TO CITY FIELD -------- */

            WebElement cityLabel = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().textContains(\"City\"));"
            ));
            cityLabel.click();

            /* -------- ENTER CITY -------- */
            informed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//android.widget.EditText[@resource-id=\"text-input-outlined\"])[3]")
                )
            );

            informed.sendKeys("Hyderabad");
            driver.hideKeyboard();

            System.out.println("Hyderabad");
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            /* ---------------- MANUAL ADDRESS FIELD ---------------- */
            WebElement ManualAddress = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.Button[@content-desc=\"Manual Address\"]")
            ));

            ManualAddress.click();
            System.out.println("✅ Manual Address Button Clicked");

            /* ---------------- LandMark Address ---------------- */

            informed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//android.widget.EditText[@resource-id=\"text-input-outlined\"])[3]")
                )
            );
            informed.sendKeys("22-143");

            /* -------- SELECT CASUALTY OPTION -------- */

            WebElement casualtyOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.ViewGroup[@content-desc=\"\uDB80\uDC2F, Casualty / Injured Person\"]")
            ));

            casualtyOption.click();

            System.out.println("Casualty option selected");
            System.out.println("All fields filled. Attempting to create emergency post...");

            /* -------- CLICK POST BUTTON -------- */

            WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[@content-desc=\"\uDB81\uDC8A, Post\"]")
            ));

            postButton.click();

            System.out.println("Post submitted successfully");


            /* ---------------- BACK TO HOME ---------------- */

            driver.navigate().back();

            System.out.println("🏠 Returned to Home Screen");
        }
}

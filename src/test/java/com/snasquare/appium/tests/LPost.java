package com.snasquare.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LPost {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Device");
        caps.setCapability("udid", "192.168.1.25:5555");
        caps.setCapability("automationName", "UiAutomator2");

        caps.setCapability("appPackage", "com.android.settings");
        caps.setCapability("appActivity", ".Settings");

        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                caps
        );

        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @Test
    public void AutomatedLPosttest() {

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

        /* ---------------- OPEN LOST POST PAGE ---------------- */
        WebElement FoundBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.TextView[@resource-id=\"button-text\" and @text=\"POST LOST ITEM\"]")
                )
        );
        FoundBtn.click();

        System.out.println("🚨 Lost Post Page Opened");

        // Handle Location Permission

        try {

            WebElement allowLocation = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")
                    )
            );

            allowLocation.click();

            System.out.println("✅ Location permission granted (While using the app)");

        } catch (Exception e) {

            System.out.println("⚠️ Permission popup not displayed");

        }

        // Click Catogery button
        WebElement CatogeryBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.ViewGroup[@content-desc=\"\uDB82\uDC32, Category *\"]")
                )
        );
        CatogeryBtn.click();

        WebElement PersonalitemBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//android.widget.TextView[@text=\"\uDB80\uDD31\"])[1]")
                )
        );
        PersonalitemBtn.click();

        // Click Sub-Catogery button
        WebElement SubCatogeryBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.ViewGroup[@content-desc=\"\uDB80\uDEC1, Subcategory *\"]")
                )
        );
        SubCatogeryBtn.click();

        WebElement WatchesBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//android.widget.TextView[@text=\"\uDB80\uDD31\"])[3]")
                )
        );
        WatchesBtn.click();

        /* ---------------- ITEM DETAILS ---------------- */
        WebElement Itemdtls = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//android.widget.EditText[@resource-id=\"text-input-outlined\"])[1]")
                )
        );

        Itemdtls.sendKeys("Lost my Watch on Road");

        /* ---------------- ADD MORE DETAILS ---------------- */
        WebElement AMD = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.EditText[@resource-id=\"text-input-outlined\" and @text=\"Add more details...\"]")
                )
        );

        AMD.sendKeys("I have Lost my Favourite Watch in Andhra Pradesh Nellore in Kotamitta Shadi Manzil Road");

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        WebElement firstImage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_check\"])[1]")
        ));

        firstImage.click();
        System.out.println("First image selected");

        // Click ADD / DONE button
        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.Button[@resource-id=\"com.google.android.providers.media.module:id/button_add\"]")
                )
        );
        addButton.click();

        System.out.println("✅ Image uploaded successfully");


        /* -------- SCROLL TO CITY FIELD -------- */

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"City\"));"
        ));

        /* -------- ENTER CITY -------- */

        WebElement cityField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//android.widget.EditText[@resource-id=\"text-input-outlined\"])[1]")
                )
        );

        cityField.click();
        cityField.sendKeys("Nellore");

        driver.hideKeyboard();

        System.out.println("✅ City entered: Nellore");


        /* ---------------- SET LOCATION ---------------- */
        WebElement Location = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc=\"Selected: 37.4220, -122.0840, \uDB80\uDF52\"]")
        ));

        Location.click();

        /* ---------------- CURRENT LOCATION ---------------- */
        WebElement Cloc = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.Button[@content-desc=\"Current\"]")
        ));

        Cloc.click();
        System.out.println("✅ Current Location Set");
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        /* ---------------- CONFIRM LOCATION ---------------- */
        WebElement CLocation = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.Button[@content-desc=\"Confirm Location\"]")
        ));

        CLocation.click();
        System.out.println("✅ Location Set Successful");
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        /* ---------------- MANUAL ADDRESS FIELD ---------------- */
        WebElement ManualAddress = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.Button[@content-desc=\"Manual Address\"]")
        ));

        ManualAddress.click();
        System.out.println("✅ Manual Address Button Clicked");
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        /* ---------------- LandMark Address ---------------- */

        WebElement Lndmrk= wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//android.widget.EditText[@resource-id=\"text-input-outlined\"])[1]")
                )
        );
        Lndmrk.sendKeys("22-142");


        /* -------- CLICK POST BUTTON -------- */

        WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.Button[@content-desc=\"Post\"]")
        ));

        postButton.click();

        System.out.println("Post submitted successfully");

    }
}

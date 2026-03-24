package com.snasquare.appium.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.snasquare.appium.managers.DriverManager;
import java.util.List;

public class ExploreAppTest {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        DriverManager.initializeDriver();
        driver = (AndroidDriver) DriverManager.getDriver();
    }

    @Test
    public void exploreApp() throws InterruptedException {
        System.out.println("\n🔍 Exploring app...\n");

        // Find all clickable elements on welcome screen
        List<WebElement> clickable = driver.findElements(By.xpath("//*[@clickable='true']"));
        System.out.println("Clickable elements: " + clickable.size());

        // Click the Login button
        WebElement loginBtn = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Login']"));
        System.out.println("Clicking Login button...");
        loginBtn.click();

        Thread.sleep(3000);

        // Now find all elements on login screen
        System.out.println("\n📱 Elements on Login Screen:");

        List<WebElement> allElements = driver.findElements(By.xpath("//*"));
        for (WebElement el : allElements) {
            String text = el.getText();
            String resourceId = el.getAttribute("resource-id");
            String className = el.getAttribute("class");

            if (text != null && !text.isEmpty() || (resourceId != null && !resourceId.isEmpty())) {
                System.out.println("  Class: " + className);
                System.out.println("  Text: " + text);
                System.out.println("  Resource-ID: " + resourceId);
                System.out.println("  --------------------");
            }
        }

        // Find EditText fields
        List<WebElement> editTexts = driver.findElements(By.className("android.widget.EditText"));
        System.out.println("\n📝 EditText fields: " + editTexts.size());
        for (int i = 0; i < editTexts.size(); i++) {
            WebElement el = editTexts.get(i);
            System.out.println("  EditText " + i + " Resource-ID: " + el.getAttribute("resource-id"));
        }
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
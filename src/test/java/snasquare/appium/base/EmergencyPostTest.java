package com.snasquare.appium.tests;

import com.snasquare.appium.pages.LoginPage;
import com.snasquare.appium.pages.EmergencyPostPage;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class EmergencyPostTest extends BaseTest {

    @Test
    public void createEmergencyPostTest() {

        LoginPage loginPage = new LoginPage(driver);
        EmergencyPostPage emergencyPage = new EmergencyPostPage(driver);

        // Login steps
        loginPage.verifyWelcomeScreen();
        loginPage.clickWelcomeLoginButton();
        loginPage.verifyLoginScreen();
        loginPage.enterMobileNumber("9876543210");
        loginPage.enterPassword("password123");
        loginPage.clickLoginSubmitButton();
        loginPage.verifyLoginSuccess();

        // Emergency Post steps
        emergencyPage.clickEmergencyButton();
        emergencyPage.verifyEmergencyPage();
        emergencyPage.enterTitle("Need Blood Urgently");
        emergencyPage.enterDescription("O+ blood required immediately");
        emergencyPage.enterLocation("Hyderabad");
        emergencyPage.clickSubmitButton();
        emergencyPage.verifyPostCreated();
    }
}

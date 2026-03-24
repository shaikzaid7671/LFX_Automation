package com.snasquare.appium.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static com.snasquare.appium.utils.ConfigLoader.getProperties;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;
    private static final Properties config = getProperties();

    /* ================= GET DRIVER ================= */

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    private static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }

    /* ================= INITIALIZE DRIVER ================= */

    public static void initializeDriver() {
        try {

            DesiredCapabilities caps = new DesiredCapabilities();

            /* ---------- PLATFORM ---------- */
            caps.setCapability("platformName",
                    config.getProperty("platform", "Android"));
            caps.setCapability("appium:automationName",
                    config.getProperty("automation.name", "UiAutomator2"));
            caps.setCapability("appium:deviceName",
                    config.getProperty("device.name", "emulator-5554"));
            caps.setCapability("appium:platformVersion",
                    config.getProperty("platform.version", "13"));

            /* ---------- APP ---------- */
            String appPath = getAbsoluteAppPath();
            caps.setCapability("appium:app", appPath);
            caps.setCapability("appium:appPackage",
                    config.getProperty("app.package"));
            caps.setCapability("appium:appActivity",
                    config.getProperty("app.activity"));

            /* ---------- DEVICE SETTINGS ---------- */
            caps.setCapability("appium:noReset",
                    Boolean.parseBoolean(config.getProperty("no.reset", "false")));
            caps.setCapability("appium:fullReset",
                    Boolean.parseBoolean(config.getProperty("full.reset", "false")));
            caps.setCapability("appium:dontStopAppOnReset",
                    Boolean.parseBoolean(config.getProperty("dont.stop.app.on.reset", "true")));
            caps.setCapability("appium:autoGrantPermissions",
                    Boolean.parseBoolean(config.getProperty("auto.grant.permissions", "true")));
            caps.setCapability("appium:ignoreHiddenApiPolicyError",
                    Boolean.parseBoolean(config.getProperty("ignore.hidden.api.policy", "true")));
            caps.setCapability("appium:disableWindowAnimation",
                    Boolean.parseBoolean(config.getProperty("disable.window.animation", "true")));
            caps.setCapability("appium:skipUnlock",
                    Boolean.parseBoolean(config.getProperty("skip.unlock", "true")));

            /* ---------- TIMEOUTS ---------- */
            caps.setCapability("appium:newCommandTimeout",
                    Integer.parseInt(config.getProperty("new.command.timeout", "300")));

            caps.setCapability("appium:adbExecTimeout",
                    Integer.parseInt(config.getProperty("adb.exec.timeout", "20000")));

            caps.setCapability("appium:androidInstallTimeout",
                    Integer.parseInt(config.getProperty("android.install.timeout", "120000")));

            /* ---------- APPIUM SERVER ---------- */
            URL serverUrl = new URL(
                    config.getProperty("appium.server.url", "http://127.0.0.1:4723") +
                            config.getProperty("appium.server.path", "/")
            );

            System.out.println("=========================================");
            System.out.println("🚀 Initializing Android Driver");
            System.out.println("📱 Device      : " + config.getProperty("device.name"));
            System.out.println("📦 App Path    : " + appPath);
            System.out.println("🔗 Appium URL  : " + serverUrl);
            System.out.println("=========================================");

            AndroidDriver androidDriver = new AndroidDriver(serverUrl, caps);

            androidDriver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(
                            Long.parseLong(config.getProperty("implicit.wait", "30"))
                    )
            );

            setDriver(androidDriver);

            System.out.println("✅ Driver initialized successfully");
            System.out.println("📱 Current Package : " + androidDriver.getCurrentPackage());
            System.out.println("📱 Current Activity: " + androidDriver.currentActivity());

        } catch (Exception e) {
            throw new RuntimeException("❌ Driver initialization failed", e);
        }
    }

    /* ================= QUIT DRIVER ================= */

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            System.out.println("🧹 Driver quit successfully");
        }
    }

    /* ================= APPIUM SERVER ================= */

    public static void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(30))
                .build();
        service.start();
        System.out.println("✅ Appium Server Started");
    }

    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("🛑 Appium Server Stopped");
        }
    }

    /* ================= UTIL ================= */

    private static String getAbsoluteAppPath() {

        String appPath = System.getProperty("user.dir")
                + File.separator + "apps"
                + File.separator + "LFXTestV0.0.3.apk";

        File appFile = new File(appPath);

        System.out.println("=========================================");
        System.out.println("USER DIR: " + System.getProperty("user.dir"));
        System.out.println("FINAL APK PATH: " + appPath);
        System.out.println("=========================================");

        if (!appFile.exists()) {
            throw new RuntimeException("❌ APK not found at: " + appFile.getAbsolutePath());
        }

        System.out.println("✅ APK found at: " + appFile.getAbsolutePath());
        return appFile.getAbsolutePath();
    }
}
package snasquare.appium.base;
import com.snasquare.appium.managers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        System.out.println("\n=========================================");
        System.out.println("🏁 Starting LFX Mobile Test Suite");
        System.out.println("=========================================\n");

        // Optional: Auto-start Appium server if needed
        // DriverManager.startAppiumServer();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("\n-----------------------------------------");
        System.out.println("🔄 Setting up test...");
        System.out.println("-----------------------------------------\n");

        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("\n-----------------------------------------");
        System.out.println("🧹 Cleaning up test...");
        System.out.println("-----------------------------------------\n");

        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void globalTeardown() {
        System.out.println("\n=========================================");
        System.out.println("🏁 LFX Mobile Test Suite Completed");
        System.out.println("=========================================\n");

        // Optional: Stop Appium server if you started it
        // DriverManager.stopAppiumServer();
    }
}

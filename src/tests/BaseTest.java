package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {
    protected AndroidDriver driver;
    private static final String DRIVER_URL = "http://127.0.0.1:4723/wd/hub";

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/Desktop/java/appiumAuto/apks/wikipedia.apk");
        driver = new AndroidDriver(new URL(DRIVER_URL), capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

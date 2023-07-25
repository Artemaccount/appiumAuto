package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected AppiumDriver driver;
    private static final String DRIVER_URL = "http://127.0.0.1:4723";
    private static final String ANDROID_PLATFORM_NAME = "android";
    private static final String IOS_PLATFORM_NAME = "iOS";

    @BeforeEach
    public void setUp() throws Exception {
        driver = getDriverByEnv();
    }

    private AppiumDriver getDriverByEnv() throws MalformedURLException {
        String platformName = System.getenv("platformName");
        switch (platformName) {
            case ANDROID_PLATFORM_NAME:
                DesiredCapabilities androidCaps = new DesiredCapabilities();
                androidCaps.setCapability("deviceOrientation", "portrait");
                androidCaps.setCapability("platformName", "android");
                androidCaps.setCapability("deviceName", "AndroidTestDevice");
                androidCaps.setCapability("platformVersion", "14");
                androidCaps.setCapability("automationName", "UiAutomator2");
                androidCaps.setCapability("appPackage", "org.wikipedia");
                androidCaps.setCapability("appActivity", ".main.MainActivity");
                androidCaps.setCapability("app", "/Users/user/Desktop/java/appiumAuto/apks/wikipedia.apk");
                return new AndroidDriver(new URL(DRIVER_URL), androidCaps);
            case IOS_PLATFORM_NAME:
                DesiredCapabilities iosCaps = new DesiredCapabilities();
                iosCaps.setCapability("deviceOrientation", "portrait");
                iosCaps.setCapability("platformName", "iOS");
                iosCaps.setCapability("deviceName", "iPhone 14");
                iosCaps.setCapability("platformVersion", "16.2");
                iosCaps.setCapability("automationName", "XCUITest");
                iosCaps.setCapability("app", "/Users/user/Desktop/java/appiumAuto/apks/wikipedia.app");
                return new IOSDriver(new URL(DRIVER_URL), iosCaps);
            default:
                throw new RuntimeException("unknown platform name " + platformName);
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

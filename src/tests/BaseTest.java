package tests;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FirstTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","14");
        capabilities.setCapability("automationName","appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/user/Desktop/java/appiumAuto/apks/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void secondTest(){
        String expectedWord = "Java";
        waitAndClickTo(By.xpath("//*[@text='Skip']"),
                "cant click to skip button");

        waitAndClickTo(By.xpath("//*[contains(@resource-id, 'search_container')]"),
                "cant click to search container");

        waitAndSendKeysTo(By.xpath("//*[contains(@resource-id, 'search_src_text')]"),
                expectedWord, "cant sendkeys to search input");

        List<WebElement> list = driver.findElements(By.xpath("//*[contains(@resource-id, 'page_list_item_title')]"));
        List<String> textList = list.stream().map(WebElement::getText).collect(Collectors.toList());
        textList.forEach(s-> Assertions.assertTrue(s.contains(expectedWord)));
    }

    private void waitForElementDisappeared(By by, String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private void waitAndClickTo(By by, String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    private void waitAndSendKeysTo(By by, String keys, String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).sendKeys(keys);
    }

    private WebElement assertElementHasText(By by, String expectedText, String errorMessage){
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        Assertions.assertEquals(expectedText, actualText, errorMessage);
        return element;
    }
}
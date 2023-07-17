import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/Desktop/java/appiumAuto/apks/wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    @Test
    public void saveTwoArticlesTest() {
        waitAndClickTo(By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "cant click to skip button");

        waitAndClickTo(By.id("org.wikipedia:id/search_container"),
                "cannot click to Search Wikipedia");

        waitAndClickTo(By.id("org.wikipedia:id/search_container"),
                "cannot click to Search Wikipedia");

        waitAndSendKeysTo(By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot send keys to Search Wikipedia");

        List<WebElement> resultList = waitForList(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup"),
                "list size less than 1");

        waitAndClickTo(resultList.get(0), "cannot click to first element of list");

        assertElementPresent(By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']"));
    }

    private void assertElementPresent(By by) {
        boolean isElementPresented = true;
        try {
            isElementPresented = driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            isElementPresented = false;
        } finally {
            Assertions.assertTrue(isElementPresented, "element is not presented");
        }
    }

    private void swipeElementToLeft(WebElement element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int middleY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
        int endX = element.getLocation().getX();

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), startX, middleY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), endX, middleY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    private void waitForElementDisappeared(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementVisibility(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private List<WebElement> waitForList(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        return driver.findElements(by);
    }

    private void waitAndClickTo(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    private void waitAndClickTo(WebElement element, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void waitAndSendKeysTo(By by, String keys, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).sendKeys(keys);
    }

    private WebElement assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        Assertions.assertEquals(expectedText, actualText, errorMessage);
        return element;
    }
}
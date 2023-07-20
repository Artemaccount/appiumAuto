package page_objects;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {
    private static final String SKIP_BUTTON_XPATH = "//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";
    private static final String READING_LIST_ID = "org.wikipedia:id/nav_tab_reading_lists";
    private static final String ITEM_TITLE_CONTAINER_ID = "org.wikipedia:id/item_title_container";
    private static final String NAVIGATE_UP_BUTTON_XPATH = "//android.widget.ImageButton[@content-desc='Navigate up']";
    private static final String PAGE_LIST_ITEM_TITLE_XPATH = "//*[contains(@resource-id, 'page_list_item_title')]";
    private static final String ITEM_TITLE_XPATH = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']";
    private AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


    public void skipOnboarding(){
        waitAndClickTo(By.xpath(SKIP_BUTTON_XPATH),
                "cant click to skip button");
    }

    protected void waitForElementDisappeared(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement waitForElementVisibility(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<WebElement> waitForList(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        return driver.findElements(by);
    }

    protected void waitAndClickTo(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    public void openSavedList(){
        waitAndClickTo(By.id(READING_LIST_ID), "cannot click to saved");
        waitAndClickTo(By.id(ITEM_TITLE_CONTAINER_ID), "cannot click to saved list");
    }

    protected void waitAndClickTo(WebElement element, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickNavigateUpButton(){
        waitAndClickTo(By.xpath(NAVIGATE_UP_BUTTON_XPATH),
                "cannot click to navigate up button");
    }

    protected void waitAndSendKeysTo(By by, String keys, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).sendKeys(keys);
    }

    public void swipeElementToLeft(WebElement element) {
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

    protected WebElement assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        Assertions.assertEquals(expectedText, actualText, errorMessage);
        return element;
    }

    public void checkSearchPageClosed(){
        waitForElementDisappeared(By.xpath(PAGE_LIST_ITEM_TITLE_XPATH),
                "element was found");
    }

    public void checkTitlePresent(WebElement element) {
        waitAndClickTo(element, "cannot click to element");
        By by = By.xpath(ITEM_TITLE_XPATH);
        boolean isElementPresented = true;
        try {
            isElementPresented = driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            isElementPresented = false;
        } finally {
            Assertions.assertTrue(isElementPresented, "element is not presented");
        }
    }
}

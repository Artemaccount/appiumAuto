package lib.page_objects.abstract_page_objects;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class MainPageObject {
    protected static String SKIP_BUTTON_XPATH;
    protected static String READING_LIST_ID;
    protected static String ITEM_TITLE_CONTAINER_ID;
    protected static String NAVIGATE_UP_BUTTON_XPATH;
    protected static String PAGE_LIST_ITEM_TITLE_XPATH;
    protected static String ITEM_TITLE_XPATH;
    private static final String cancelButton = "xpath://XCUIElementTypeStaticText[@name='Cancel']";

    private static final String closeButtonId = "id:Close";

    private AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


    public void skipOnboarding(){
        waitAndClickTo(SKIP_BUTTON_XPATH,
                "cant click to skip button");
    }

    protected void waitForElementDisappeared(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement waitForElementVisibility(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForElementVisibilityIOS(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        int maxTry = 10;
        boolean exists = false;
        for (int i = 0; i < maxTry; i++) {
            exists = !driver.findElements(by).isEmpty();
            if(exists){
                break;
            } else {
                waitForSeconds(1);
            }
        }
        if(!exists){
            throw new RuntimeException(errorMessage);
        }
    }
    private void waitForSeconds(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<WebElement> waitForList(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        return driver.findElements(by);
    }

    protected void waitAndClickTo(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    public void openSavedList(){
        waitAndClickTo(READING_LIST_ID, "cannot click to saved");
        if (Platform.getInstance().isAndroid()) {
            waitAndClickTo(ITEM_TITLE_CONTAINER_ID, "cannot click to saved list");
        } else {
            clickCloseSyncMenu();
        }

    }

    public void clickCloseSyncMenu(){
        waitAndClickTo(closeButtonId, "cannot close window");
    }

    protected void waitAndClickTo(WebElement element, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickNavigateUpButton(){
        waitAndClickTo(NAVIGATE_UP_BUTTON_XPATH,
                "cannot click to navigate up button");
    }

    public void clickCancel(){
        waitAndClickTo(cancelButton,
                "cannot click to navigate up button");
    }

    protected void waitAndSendKeysTo(String locator, String keys, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(by)).sendKeys(keys);
    }

    public void swipeElementToLeft(WebElement element) {
     if(Platform.getInstance().isAndroid()){
         swipeElementToLeftAndroid(element);
     } else {
         swipeElementToLeftIOS(element);
     }
    }

    public void swipeElementToLeftAndroid(WebElement element){
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

    public void swipeElementToLeftIOS(WebElement element) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int rightX = element.getLocation().getX() + 1000;
        int leftX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        fingerSwipe(rightX, middleY, leftX, middleY, 300);
    }

    private void fingerSwipe(int startX, int startY, int endX, int endY, long timeInMillis) {
        PointerInput touchAction = new PointerInput(PointerInput.Kind.TOUCH, "touchAction");
        Interaction moveToStart = touchAction.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY);
        Interaction pressDown = touchAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = touchAction.createPointerMove(Duration.ofMillis(timeInMillis), PointerInput.Origin.viewport(), endX, endY);
        Interaction pressUp = touchAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(touchAction, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);

        driver.perform(Arrays.asList(swipe));
    }

    public void checkSearchPageClosed(){
        waitForElementDisappeared(PAGE_LIST_ITEM_TITLE_XPATH,
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

    private By getLocatorByString(String locatorWithBy){
        String[] parts = locatorWithBy.split(":", 2);
        String by = parts[0];
        String locator = parts[1];
        switch (by){
            case "xpath":
                return By.xpath(locator.toString());
            case "id":
                return By.id(locator.toString());
            default:
                throw new IllegalArgumentException("by not found " + by);
        }
    }
}

package lib.page_objects.abstract_page_objects;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public abstract class SearchPageObject extends MainPageObject {

    protected static String SEARCH_CONTAINER_ID;
    protected static String SEARCH_LINE_XPATH;
    protected static String SEARCH_LIST_XPATH;
    protected static String PAGE_LIST_ITEM_XPATH;
    protected static String CLOSE_BUTTON_XPATH;
    protected static String SAVED_LIST_MAIN_ID;
    protected static String SAVED_LIST_CONTAINER_ID;
    protected static String SEARCH_RESULT_BY_SUBSTRING_TPL;
    protected static String SEARCH_RESULT_BY_TITLE_AND_DESC_TPL;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultByTitleAndDesc(String title, String desc) {
        return SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{TITLE}", title)
                .replace("{DESC}", desc);
    }

    /* TEMPLATE METHODS */

    public void waitForElementByTitleAndDescription(String title, String description){
        String xpath = getResultByTitleAndDesc(title, description);
        waitForElementVisibility(xpath, String.format("element with title %s and desc %s is not visible",title, description));
    }

    public void searchInWiki(String word) {
        waitAndClickTo(SEARCH_CONTAINER_ID,
                "cannot click to Search Wikipedia");

        waitAndClickTo(SEARCH_CONTAINER_ID,
                "cannot click to Search Wikipedia");

        waitAndSendKeysTo(SEARCH_LINE_XPATH,
                word,
                "cannot send keys to Search Wikipedia");
    }

    public List<WebElement> getSearchResultList() {
        return waitForList(SEARCH_LIST_XPATH,
                "list size less than 1");
    }

    public void checkResultListContainsTitle(List<WebElement> resultList, String expectedWord) {
        List<String> textList = resultList.stream().map(s -> s.findElement(By.xpath(PAGE_LIST_ITEM_XPATH)).getText()).collect(Collectors.toList());
        textList.forEach(s -> Assertions.assertTrue(s.contains(expectedWord)));
    }

    public void clickCloseButton() {
        waitAndClickTo(CLOSE_BUTTON_XPATH,
                "cant click to close button");
    }

    public void openSavedList() {
        waitAndClickTo(SAVED_LIST_MAIN_ID, "cannot click to saved");
        waitAndClickTo(SAVED_LIST_CONTAINER_ID, "cannot click to saved list");
    }
}

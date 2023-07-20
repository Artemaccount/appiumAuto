package page_objects;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_CONTAINER_ID = "org.wikipedia:id/search_container";
    private static final String SEARCH_LINE_XPATH = "//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']";
    private static final String SEARCH_LIST_XPATH = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
    private static final String PAGE_LIST_ITEM_XPATH = "//android.widget.TextView[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]";
    private static final String CLOSE_BUTTON_XPATH = "//*[contains(@resource-id, 'search_close_btn')]";
    private static final String SAVED_LIST_MAIN_ID = "org.wikipedia:id/nav_tab_reading_lists";
    private static final String SAVED_LIST_CONTAINER_ID = "org.wikipedia:id/item_title_container";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void searchInWiki(String word) {
        waitAndClickTo(By.id(SEARCH_CONTAINER_ID),
                "cannot click to Search Wikipedia");

        waitAndClickTo(By.id(SEARCH_CONTAINER_ID),
                "cannot click to Search Wikipedia");

        waitAndSendKeysTo(By.xpath(SEARCH_LINE_XPATH),
                word,
                "cannot send keys to Search Wikipedia");
    }

    public List<WebElement> getSearchResultList() {
        return waitForList(
                By.xpath(SEARCH_LIST_XPATH),
                "list size less than 1");
    }

    public void checkResultListContainsTitle(List<WebElement> resultList, String expectedWord) {
        List<String> textList = resultList.stream().map(s -> s.findElement(By.xpath(PAGE_LIST_ITEM_XPATH)).getText()).collect(Collectors.toList());
        textList.forEach(s -> Assertions.assertTrue(s.contains(expectedWord)));
    }

    public void clickCloseButton() {
        waitAndClickTo(By.xpath(CLOSE_BUTTON_XPATH),
                "cant click to close button");
    }

    public void openSavedList() {
        waitAndClickTo(By.id(SAVED_LIST_MAIN_ID), "cannot click to saved");
        waitAndClickTo(By.id(SAVED_LIST_CONTAINER_ID), "cannot click to saved list");
    }
}

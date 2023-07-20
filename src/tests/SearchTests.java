package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import page_objects.MainPageObject;
import page_objects.SearchPageObject;

import java.util.List;

public class SearchTests extends BaseTest {
    @Test
    public void searchListContainsWordTest() {
        String expectedWord = "Java";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.skipOnboarding();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.searchInWiki(expectedWord);

        List<WebElement> list = searchPageObject.getSearchResultList();
        searchPageObject.checkResultListContainsTitle(list, expectedWord);
    }

    @Test
    public void cancelSearchTest() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.skipOnboarding();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.searchInWiki("appium");

        List<WebElement> list = searchPageObject.getSearchResultList();
        Assertions.assertTrue(list.size() > 1, "Found less than 2 articles");

        searchPageObject.clickCloseButton();
        mainPageObject.checkSearchPageClosed();
    }
}
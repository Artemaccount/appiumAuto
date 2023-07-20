package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import page_objects.ArticlePageObject;
import page_objects.MainPageObject;
import page_objects.SearchPageObject;

import java.util.List;

public class ArticleTests extends BaseTest {
    @Test
    public void deleteArticleSuccessTest() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        mainPageObject.skipOnboarding();
        searchPageObject.searchInWiki("Java");
        List<WebElement> resultList = searchPageObject.getSearchResultList();

        articlePageObject.saveArticle(resultList.get(0));

        searchPageObject.clickNavigateUpButton();
        List<WebElement> resultList2 = searchPageObject.getSearchResultList();

        articlePageObject.saveArticle(resultList2.get(1));

        searchPageObject.clickNavigateUpButton();
        searchPageObject.clickNavigateUpButton();

        mainPageObject.openSavedList();

        List<WebElement> savedList = articlePageObject.getSavedList();

        articlePageObject.swipeElementToLeft(savedList.get(0));

        List<WebElement> savedList2 = articlePageObject.getSavedList();

        Assertions.assertEquals("JavaScript", savedList2.get(0).getText());

        articlePageObject.checkArticleTitle(savedList2.get(0), "JavaScript");

    }

    @Test
    public void titlePresentTest() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        mainPageObject.skipOnboarding();
        searchPageObject.searchInWiki("Java");

        List<WebElement> resultList = searchPageObject.getSearchResultList();

        mainPageObject.checkTitlePresent(resultList.get(0));
    }
}

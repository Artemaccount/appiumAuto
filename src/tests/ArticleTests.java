package tests;

import lib.Platform;
import lib.page_objects.factories.ArticlePageObjectFactory;
import lib.page_objects.factories.MainPageObjectFactory;
import lib.page_objects.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import lib.page_objects.abstract_page_objects.ArticlePageObject;
import lib.page_objects.abstract_page_objects.MainPageObject;
import lib.page_objects.abstract_page_objects.SearchPageObject;

import java.util.List;

public class ArticleTests extends BaseTest {
    @Test
    public void deleteArticleSuccessTest() {
        MainPageObject mainPageObject = MainPageObjectFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        mainPageObject.skipOnboarding();
        searchPageObject.searchInWiki("Java");
        List<WebElement> resultList = searchPageObject.getSearchResultList();

        articlePageObject.saveArticle(resultList.get(0));

        searchPageObject.clickNavigateUpButton();
        List<WebElement> resultList2 = searchPageObject.getSearchResultList();

        articlePageObject.saveArticle(resultList2.get(1));

        searchPageObject.clickNavigateUpButton();
        if(Platform.getInstance().isIOS()){
            searchPageObject.clickCancel();
        } else {
            searchPageObject.clickNavigateUpButton();
        }

        mainPageObject.openSavedList();

        if(Platform.getInstance().isAndroid()){
            List<WebElement> savedList = articlePageObject.getSavedList();
            articlePageObject.swipeElementToLeft(savedList.get(0));

            List<WebElement> savedList2 = articlePageObject.getSavedList();
            Assertions.assertEquals("JavaScript", savedList2.get(0).getText());

            articlePageObject.checkArticleTitle(savedList2.get(0), "JavaScript");

        } else {
            List<WebElement> savedList = articlePageObject.getSavedListIos();
            articlePageObject.swipeElementToLeftIOS(savedList.get(0));
            articlePageObject.clickDeleteIOS();

            List<WebElement> savedList2 = articlePageObject.getSavedList();
            Assertions.assertEquals("Java", savedList2.get(0).getText());

            articlePageObject.checkArticleTitleIOS(savedList2.get(0), "Java");
        }

    }

    @Test
    public void titlePresentTest() {
        MainPageObject mainPageObject = MainPageObjectFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        mainPageObject.skipOnboarding();
        searchPageObject.searchInWiki("Java");

        List<WebElement> resultList = searchPageObject.getSearchResultList();

        mainPageObject.checkTitlePresent(resultList.get(0));
    }
}

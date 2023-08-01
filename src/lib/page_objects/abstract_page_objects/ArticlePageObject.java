package lib.page_objects.abstract_page_objects;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ArticlePageObject extends MainPageObject {

    protected static String SAVE_PAGE_BUTTON_ID;
    protected static String SAVED_LIST_XPATH;
    private static final String SAVED_LIST_IOS_XPATH = "xpath://XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeImage";
    private static final String DELETE_BUTTON_IOS = "id:swipe action delete";
    protected static String ARTICLE_TITLE_XPATH;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void saveArticle(WebElement element){
        waitAndClickTo(element, "cannot click to first element of list");

        waitAndClickTo(SAVE_PAGE_BUTTON_ID,
                "cannot click to page save");
    }



    public List<WebElement> getSavedList(){
        return waitForList(SAVED_LIST_XPATH,
                "cannot load saved list");
    }

    public List<WebElement> getSavedListIos(){
        return waitForList(SAVED_LIST_IOS_XPATH,
                "cannot load saved list");
    }

    public void clickDeleteIOS(){
        waitAndClickTo(DELETE_BUTTON_IOS, "cannot click delete button");
    }

    public void checkArticleTitle(WebElement element, String expectedTitle){
        waitAndClickTo(element, "cannot click to saved list item");

        String actualTitle = waitForElementVisibility(ARTICLE_TITLE_XPATH,
                "element is not visible").getText();

        Assertions.assertEquals(expectedTitle, actualTitle);
    }


    public void checkArticleTitleIOS(WebElement element, String expectedTitle){
        waitAndClickTo(element, "cannot click to saved list item");
        String xpath = ARTICLE_TITLE_XPATH.replace("TEXT", expectedTitle);
        waitForElementVisibility(xpath, "element is not visible");
    }
}

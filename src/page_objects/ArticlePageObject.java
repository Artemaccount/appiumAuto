package page_objects;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject{
    private static final String SAVE_PAGE_BUTTON_ID = "org.wikipedia:id/page_save";
    private static final String SAVED_LIST_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private static final String ARTICLE_TITLE_XPATH = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void saveArticle(WebElement element){
        waitAndClickTo(element, "cannot click to first element of list");

        waitAndClickTo("id:" + SAVE_PAGE_BUTTON_ID,
                "cannot click to page save");
    }

    public List<WebElement> getSavedList(){
        return waitForList("xpath:" + SAVED_LIST_XPATH,
                "cannot load saved list");
    }

    public void checkArticleTitle(WebElement element, String expectedTitle){
        waitAndClickTo(element, "cannot click to saved list item");

        String actualTitle = waitForElementVisibility("xpath:" + ARTICLE_TITLE_XPATH,
                "element is not visible").getText();

        Assertions.assertEquals(expectedTitle, actualTitle);
    }
}

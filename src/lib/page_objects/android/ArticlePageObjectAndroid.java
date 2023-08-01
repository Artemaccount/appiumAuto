package lib.page_objects.android;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.ArticlePageObject;

public class ArticlePageObjectAndroid extends ArticlePageObject {

    static {
        SAVE_PAGE_BUTTON_ID = "id:org.wikipedia:id/page_save";
        SAVED_LIST_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        ARTICLE_TITLE_XPATH = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']";
    }

    public ArticlePageObjectAndroid(AppiumDriver driver) {
        super(driver);
    }
}

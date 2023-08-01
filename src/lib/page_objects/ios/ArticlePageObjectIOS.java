package lib.page_objects.ios;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.ArticlePageObject;

public class ArticlePageObjectIOS extends ArticlePageObject {

    static {
       SAVE_PAGE_BUTTON_ID = "id:Save for later";
       SAVED_LIST_XPATH = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
        ARTICLE_TITLE_XPATH = "xpath:(//XCUIElementTypeStaticText[@name='TEXT'])[1]";
    }
    public ArticlePageObjectIOS(AppiumDriver driver) {
        super(driver);
    }

}

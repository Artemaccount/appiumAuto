package lib.page_objects.ios;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.SearchPageObject;

public class SearchPageObjectIOS extends SearchPageObject {
    static {
        SEARCH_CONTAINER_ID = "id:Search Wikipedia";
        SEARCH_LINE_XPATH = "id:Search Wikipedia";
        SEARCH_LIST_XPATH = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]";
        PAGE_LIST_ITEM_XPATH = "";
        CLOSE_BUTTON_XPATH = "";
        SAVED_LIST_MAIN_ID = "";
        SAVED_LIST_CONTAINER_ID = "";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "";
        SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "";
    }

    public SearchPageObjectIOS(AppiumDriver driver) {
        super(driver);
    }
}

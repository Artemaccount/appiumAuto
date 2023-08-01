package lib.page_objects.ios;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.MainPageObject;

public class MainPageObjectIOS extends MainPageObject {

    static {
         SKIP_BUTTON_XPATH = "xpath://XCUIElementTypeStaticText[@name='Skip']";
         READING_LIST_ID = "id:Saved";
         ITEM_TITLE_CONTAINER_ID = "";
         NAVIGATE_UP_BUTTON_XPATH = "id:Search";
         PAGE_LIST_ITEM_TITLE_XPATH = "";
         ITEM_TITLE_XPATH = "";
    }

    public MainPageObjectIOS(AppiumDriver driver) {
        super(driver);
    }
}

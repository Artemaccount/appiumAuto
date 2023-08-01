package lib.page_objects.android;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.MainPageObject;

public class MainPageObjectAndroid extends MainPageObject {

    static {
        SKIP_BUTTON_XPATH = "xpath://android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";
        READING_LIST_ID = "id:org.wikipedia:id/nav_tab_reading_lists";
        ITEM_TITLE_CONTAINER_ID = "id:org.wikipedia:id/item_title_container";
        NAVIGATE_UP_BUTTON_XPATH = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        PAGE_LIST_ITEM_TITLE_XPATH = "xpath://*[contains(@resource-id, 'page_list_item_title')]";
        ITEM_TITLE_XPATH = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[@class='android.widget.TextView']";
    }

    public MainPageObjectAndroid(AppiumDriver driver) {
        super(driver);
    }
}

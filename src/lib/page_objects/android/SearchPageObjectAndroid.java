package lib.page_objects.android;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.SearchPageObject;

public class SearchPageObjectAndroid extends SearchPageObject {

    static {
         SEARCH_CONTAINER_ID = "id:org.wikipedia:id/search_container";
         SEARCH_LINE_XPATH = "xpath://android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']";
         SEARCH_LIST_XPATH = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
         PAGE_LIST_ITEM_XPATH = "xpath://android.widget.TextView[contains(@resource-id,'org.wikipedia:id/page_list_item_title')]";
         CLOSE_BUTTON_XPATH = "xpath://*[contains(@resource-id, 'search_close_btn')]";
         SAVED_LIST_MAIN_ID = "id:org.wikipedia:id/nav_tab_reading_lists";
         SAVED_LIST_CONTAINER_ID = "id:org.wikipedia:id/item_title_container";
         SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text()='{SUBSTRING}']";
         SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text = '{TITLE}']/following-sibling::*[contains(@text,'{DESC}')]/..";
    }

    public SearchPageObjectAndroid(AppiumDriver driver) {
        super(driver);
    }
}

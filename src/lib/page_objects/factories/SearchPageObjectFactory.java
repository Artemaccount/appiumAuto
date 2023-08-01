package lib.page_objects.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.page_objects.abstract_page_objects.SearchPageObject;
import lib.page_objects.android.SearchPageObjectAndroid;
import lib.page_objects.ios.SearchPageObjectIOS;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new SearchPageObjectAndroid(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new SearchPageObjectIOS(driver);
        } else {
            throw new RuntimeException("unknown type of driver");
        }
    }
}

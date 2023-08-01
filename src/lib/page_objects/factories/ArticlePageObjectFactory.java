package lib.page_objects.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.page_objects.abstract_page_objects.ArticlePageObject;
import lib.page_objects.android.ArticlePageObjectAndroid;
import lib.page_objects.ios.ArticlePageObjectIOS;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new ArticlePageObjectAndroid(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new ArticlePageObjectIOS(driver);
        } else {
            throw new RuntimeException("unknown type of driver");
        }
    }
}

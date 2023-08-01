package lib.page_objects.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.page_objects.abstract_page_objects.MainPageObject;
import lib.page_objects.android.MainPageObjectAndroid;
import lib.page_objects.ios.MainPageObjectIOS;

public class MainPageObjectFactory {

    public static MainPageObject get(AppiumDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new MainPageObjectAndroid(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new MainPageObjectIOS(driver);
        } else {
            throw new RuntimeException("unknown type of driver");
        }
    }
}

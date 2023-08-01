package tests;

import org.junit.jupiter.api.Test;
import lib.page_objects.ios.WelcomePageObject;

public class GetStartedTests extends BaseTest {

    @Test
    public void testPassThroughWelcome(){
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForFreeEncyclopedia();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForNewWaysToExplore();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForHelpToMakeAppBetter();
        welcomePageObject.clickGetStartedButton();
    }
}

package lib.page_objects.ios;

import io.appium.java_client.AppiumDriver;
import lib.page_objects.abstract_page_objects.MainPageObject;

public class WelcomePageObject extends MainPageObject {
    private final String FREE_ENC_ID = "id:Свободная энциклопедия";
    private final String NEW_WAYS_ID = "id:Новые способы изучения";
    private final String ADD_OR_CHANGE_XPATH = "xpath://*[contains(@name,'Добавить или изменить предпочтительные языки')]";
    private final String HELP_MAKE_BETTER_ID = "id:Помогите сделать это приложение лучше";
    private final String NEXT_BUTTON_XPATH = "xpath://XCUIElementTypeStaticText[@name=\"Далее\"]";
    private final String GET_STARTED_BUTTON_XPATH = "xpath://XCUIElementTypeButton[@name=\"Начать\"]";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForFreeEncyclopedia(){
        this.waitForElementVisibility(FREE_ENC_ID, "cannot find element");
    }

    public void waitForNewWaysToExplore(){
        this.waitForElementVisibility(NEW_WAYS_ID, "cannot find element");
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementVisibility(ADD_OR_CHANGE_XPATH, "cannot find element");
    }

    public void waitForHelpToMakeAppBetter(){
        this.waitForElementVisibility(HELP_MAKE_BETTER_ID, "cannot find element");
    }

    public void clickNextButton(){
        waitAndClickTo(NEXT_BUTTON_XPATH, "cannot click to element");
    }

    public void clickGetStartedButton(){
        waitAndClickTo(GET_STARTED_BUTTON_XPATH, "cannot click to element");
    }
}

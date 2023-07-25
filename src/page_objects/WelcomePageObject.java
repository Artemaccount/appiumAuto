package page_objects;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{
    private final String FREE_ENC_ID = "Свободная энциклопедия";
    private final String NEW_WAYS_ID = "Новые способы изучения";
    private final String ADD_OR_CHANGE_XPATH = "//*[contains(@name,'Добавить или изменить предпочтительные языки')]";
    private final String HELP_MAKE_BETTER_ID = "Помогите сделать это приложение лучше";
    private final String NEXT_BUTTON_XPATH = "//XCUIElementTypeStaticText[@name=\"Далее\"]";
    private final String GET_STARTED_BUTTON_XPATH = "//XCUIElementTypeButton[@name=\"Начать\"]";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForFreeEncyclopedia(){
        this.waitForElementVisibility("id:" + FREE_ENC_ID, "cannot find element");
    }

    public void waitForNewWaysToExplore(){
        this.waitForElementVisibility("id:" + NEW_WAYS_ID, "cannot find element");
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementVisibility("xpath:" + ADD_OR_CHANGE_XPATH, "cannot find element");
    }

    public void waitForHelpToMakeAppBetter(){
        this.waitForElementVisibility("id:" + HELP_MAKE_BETTER_ID, "cannot find element");
    }

    public void clickNextButton(){
        waitAndClickTo("xpath:" + NEXT_BUTTON_XPATH, "cannot click to element");
    }

    public void clickGetStartedButton(){
        waitAndClickTo("xpath:" + GET_STARTED_BUTTON_XPATH, "cannot click to element");
    }
}

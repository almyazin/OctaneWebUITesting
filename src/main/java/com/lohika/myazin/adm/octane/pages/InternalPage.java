package com.lohika.myazin.adm.octane.pages;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by amyazin on 2/10/2017.
 */
public class InternalPage extends AnyPage {

    @FindBy(xpath = "//a[@ui-sref='alm-platform-landing-page' or @href='#/home']")
    public WebElement homeButton;

    @FindBy(xpath = "//div[@data-aid='open-user-profile']/img[@data-aid='user-profile']")
    public WebElement userAvatar;

    @FindBy(xpath = "//button[@data-aid='mqm-user-details-on-logout']")
    public WebElement logoutButton;

    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[@class='dropdown-toggle']")
    private WebElement mainMenuButton;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]")
    public WebElement mainMenu;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-my-items']")
    public WebElement mainMenu_myWork;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-dashboard']")
    public WebElement mainMenu_dashboard;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-platform-tree-module-release-quality-main']")
    public WebElement mainMenu_backlog;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-team-backlog']")
    public WebElement mainMenu_teamBacklog;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-platform-tree-module-product-overview-main']")
    public WebElement mainMenu_quality;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-platform-tree-module-defects-main']")
    public WebElement mainMenu_defects;

    @FindBy(xpath = "//div[contains(@*, 'mainMenu.mainMenuInfo')]//div[contains(@class, 'dropdown-menu')]//div[@data-aid='main-menu-dropdown-pipelineManagement']")
    public WebElement mainMenu_pipelines;

    @Override
    public boolean isOnThisPage() {
        return userAvatar.isDisplayed();
    }

    @Override
    public void tryToOpen() {

        MyPageFactory.getPage(driver, LoginPage.class).login();
    }

    protected void openMainMenu(){
        if (!mainMenu.isDisplayed())
            getMainMenuButton().click();
    }

    public LoginPage logout() {
        userAvatar.click();
        logoutButton.click();
        return MyPageFactory.getPage(driver, LoginPage.class);
    }

    public DefectsPage gotoDefectsPage(){
        openMainMenu();
        mainMenu_defects.click();
        return MyPageFactory.getPage(driver, DefectsPage.class);
    }

    public QualityPage gotoQualityPage(){
        openMainMenu();
        mainMenu_quality.click();
        return MyPageFactory.getPage(driver, QualityPage.class);
    }

    public InternalPage gotoHomePage(){
        homeButton.click();
        return MyPageFactory.getPage(driver, HomePage.class);
    }
}

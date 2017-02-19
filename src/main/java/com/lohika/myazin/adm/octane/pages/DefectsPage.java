package com.lohika.myazin.adm.octane.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by amyazin on 2/11/2017.
 */
public class DefectsPage extends EntitiesAreaEnabledPage{
    public DefectsPage(WebDriver driver){
        super(driver);
    }
    @Override
    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    @FindBy(xpath = "//div[@class='dropdown-toggle'][@testdata-menu-label-id='platform-tree-module-defects-main']")
    public WebElement mainMenuButton;
}

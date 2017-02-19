package com.lohika.myazin.adm.octane.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by amyazin on 2/11/2017.
 */
public class HomePage extends InternalPage {
    @Override
    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    @FindBy(xpath = "//div[@class='dropdown-toggle'][@testdata-menu-label-id='alm-platform-landing-page']")
    public WebElement mainMenuButton;
}

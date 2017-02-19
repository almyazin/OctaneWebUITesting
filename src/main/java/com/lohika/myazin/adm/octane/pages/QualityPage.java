package com.lohika.myazin.adm.octane.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by amyazin on 2/11/2017.
 */
public class QualityPage extends EntitiesAreaEnabledPage{
    public QualityPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getMainMenuButton() {
        return mainMenuButton;
    }

    @FindBy(xpath = "//div[@class='dropdown-toggle'][@testdata-menu-label-id='platform-tree-module-product-overview-main']")
    public WebElement mainMenuButton;
}

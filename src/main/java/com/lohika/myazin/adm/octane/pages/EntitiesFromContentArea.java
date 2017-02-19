package com.lohika.myazin.adm.octane.pages;

import com.lohika.myazin.adm.octane.factories.EnablingRequired;
import com.lohika.myazin.model.EntitiesGridViewContainer;
import com.lohika.myazin.model.GridViewColumnHeader;
import com.lohika.myazin.model.GridViewFieldChooserContainer;
import com.lohika.myazin.model.GridViewRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by amyazin on 2/11/2017.
 */
public class EntitiesFromContentArea extends AbstractContainedPage {

    @FindBy(xpath = "//div[contains(@class, 'entities-container') and @data-aid='entities-container']")
    public WebElement entitiesContainer;

    //@FindBy(xpath = "//div[@data-aid='toolbar']//ul[contains(@class, 'toolbar')]//div[contains(@class, 'cols') and contains(@class, 'minor')]//button[@icon='f-filter-close']")
    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'minor')]//button[@icon='f-filter-close']")
    public WebElement hideFilterPaneButton;

    //@FindBy(xpath = "//div[@data-aid='toolbar']//ul[contains(@class, 'toolbar')]//div[contains(@class, 'cols') and contains(@class, 'minor')]//button[@icon='f-filter-open']")
    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'minor')]//button[@icon='f-filter-open']")
    public WebElement showFilterPaneButton;

    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'minor')]//button[contains(@data-aid, 'entities-container-toolbar-display-filter')]")
    public WebElement displayFilterPaneButton;

    //@FindBy(xpath = "//div[@data-aid='toolbar']//ul[contains(@class, 'toolbar')]//div[contains(@class, 'cols') and contains(@class, 'major')]//button[@grid-field-chooser]")
    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'major')]//button[@grid-field-chooser]")
    //@VisibilityRquired
    @EnablingRequired
    public WebElement chooseColumnsButton;

    /*//@FindBy(xpath = "//div[@data-aid='toolbar']//ul[contains(@class, 'toolbar')]//div[contains(@class, 'cols') and contains(@class, 'major')]//div[contains(@class, 'grid-field-chooser')]")
    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'major')]//div[contains(@class, 'grid-field-chooser')]")
    public WebElement _gridViewFieldChooserContainer;*/

    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'major')]//div[contains(@class, 'grid-field-chooser')]")
    public GridViewFieldChooserContainer _gridViewFieldChooserContainer;

    /*//@FindBy(xpath = "//div[@data-aid='toolbar']//ul[contains(@class, 'toolbar')]//div[contains(@class, 'cols') and contains(@class, 'major')]//div[@class='grid-field-chooser']//div[contains(@class, 'field-item')]")
    @FindBy(xpath = "//div[@data-aid='toolbar']//div[contains(@class, 'cols') and contains(@class, 'major')]//div[@class='grid-field-chooser']//div[contains(@class, 'field-item')]")
    public List<GridViewFieldChooser> gridFieldChooser;*/

    //@FindBy(xpath = "//li[contains(@class, 'toolbar-toggle-button-group')]//div[contains(@data-aid, 'smart-list-view')]")
    @FindBy(xpath = "//*[contains(@class, 'toolbar-toggle-button-group')]//*[contains(@data-aid, 'smart-list-view')]")
    public WebElement smartListViewToggleButton;

    //@FindBy(xpath = "//li[contains(@class, 'toolbar-toggle-button-group')]//div[contains(@data-aid, 'grid-view')]")
    @FindBy(xpath = "//*[contains(@class, 'toolbar-toggle-button-group')]//*[contains(@data-aid, 'grid-view')]")
    public WebElement gridViewToggleButton;

    @FindBy(xpath = "//div[@id='wrapped_grid']")
    public EntitiesGridViewContainer entitiesGridViewContainer;

    @Override
    public EntitiesAreaEnabledPage getParentPage() {
        return (EntitiesAreaEnabledPage) parentPage;
    }

    @Override
    public boolean isOnThisPage() {
        return entitiesContainer.isDisplayed();
    }

    @Override
    public void tryToOpen() { }

    public EntitiesAreaEnabledPage switchToSmartListView(){
        smartListViewToggleButton.click();
        return (EntitiesAreaEnabledPage) parentPage;
    }

    public EntitiesAreaEnabledPage switchToGridView(){
        gridViewToggleButton.click();
        return (EntitiesAreaEnabledPage) parentPage;
    }

    public boolean isInSmartListView(){
        return smartListViewToggleButton.findElement(By.cssSelector("svg")).getAttribute("name").equals("s-toggle-fatlines-view-on"); // s-toggle-fatlines-view-off otherwise
    }

    public boolean isInGridView(){
        return gridViewToggleButton.findElement(By.cssSelector("svg")).getAttribute("name").equals("s-toggle-grid-view-on"); // s-toggle-grid-view-off otherwise
    }

    /*public boolean isGridFieldChooserDisplayed(){
        return _gridViewFieldChooserContainer.isDisplayed();
    }*/

    public EntitiesAreaEnabledPage openGridViewFieldChooser() {
        if (_gridViewFieldChooserContainer.isDisplayed())
            chooseColumnsButton.click();

        return (EntitiesAreaEnabledPage) parentPage;
    }

    public EntitiesAreaEnabledPage closeGridViewFieldChooser() {
        if (_gridViewFieldChooserContainer.isDisplayed())
            chooseColumnsButton.click();
        return (EntitiesAreaEnabledPage) parentPage;
    }
}

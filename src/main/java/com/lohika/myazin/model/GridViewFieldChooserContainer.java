package com.lohika.myazin.model;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import com.lohika.myazin.adm.octane.pages.EntitiesAreaEnabledPage;
import com.lohika.myazin.adm.octane.pages.EntitiesFromContentArea;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by amyazin on 2/15/2017.
 */
public class GridViewFieldChooserContainer extends AbstractContainer {

    @FindBy(xpath = ".//div[contains(@class, 'field-item')]")
    public List<GridViewFieldChooser> gridFieldChooser;

    public GridViewFieldChooserContainer(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntitiesFromContentArea getParent() {
        return (EntitiesFromContentArea) super.getParent();
    }

    public EntitiesAreaEnabledPage open() {
        if (!isDisplayed()) {
            //MyPageFactory.getPage(driver, EntitiesFromContentArea.class).chooseColumnsButton.click();
            getParent().chooseColumnsButton.click();
        }
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public EntitiesAreaEnabledPage close() {
        if (isDisplayed())
            MyPageFactory.getPage(driver, EntitiesFromContentArea.class).chooseColumnsButton.click();
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public EntitiesAreaEnabledPage checkGridFieldChooser(String field) {
        gridFieldChooser.stream().filter(c -> c.fieldName.equals(field)).findFirst()
                .ifPresent(c -> c.check());
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public EntitiesAreaEnabledPage uncheckGridFieldChooser(String field) {
        gridFieldChooser.stream().filter(c -> c.fieldName.equals(field)).findFirst()
                .ifPresent(c -> c.uncheck());
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public EntitiesAreaEnabledPage checkGridFieldChoosers(List<String> fields) {
        gridFieldChooser.stream().filter(fields::contains).forEach(c -> c.check());
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public EntitiesAreaEnabledPage uncheckGridFieldChoosers(List<String> fields) {
        gridFieldChooser.stream().filter(fields::contains).forEach(c -> c.uncheck());
        return MyPageFactory.getPage(driver, EntitiesAreaEnabledPage.class);
    }

    public GridViewFieldChooser getGridFieldChooser(String column) {
        return gridFieldChooser.stream().filter(c -> c.fieldName.equals(column)).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There're no such field: %s", column)));
    }
}

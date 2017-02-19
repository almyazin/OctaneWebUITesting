package com.lohika.myazin.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by amyazin on 2/18/2017.
 */
public class GridViewRow extends AbstractContainer {

    @FindBy(xpath = "./div")
    public List<GridViewCell> cells;

    public GridViewRow(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntitiesGridViewContainer getParent() {
        return (EntitiesGridViewContainer) super.getParent();
    }
}

package com.lohika.myazin.model;

import com.lohika.myazin.adm.octane.pages.EntitiesFromContentArea;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by amyazin on 2/18/2017.
 */
public class EntitiesGridViewContainer extends AbstractContainer{

    @FindBy(xpath = "//div[@id='wrapped_grid']//div[contains(@class, 'slick-header-columns')]/div")
    public List<GridViewColumnHeader> gridViewColumnsHeaders;

    @FindBy(xpath = "//div[@id='wrapped_grid']/div/div/div[contains(@class, 'slick-row')]")
    public List<GridViewRow> gridViewRows;

    public EntitiesGridViewContainer(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntitiesFromContentArea getParent() {
        return (EntitiesFromContentArea) super.getParent();
    }

    public GridViewColumnHeader getGridViewColumnHeader(String column){
        return gridViewColumnsHeaders.stream().filter(c -> c.name.equals(column)).findFirst().orElse(null);
    }
}

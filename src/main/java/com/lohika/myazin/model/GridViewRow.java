package com.lohika.myazin.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isSelected(){
        return cells.size() > 0 ? cells.get(0).isChecked() : false;
    }

    public int getItemIndex(){
        String s = "ui-widget-content slick-row odd item-id-5001 item-index-1";
        String p = ".*\\bitem-index-(\\d+)\\b.*";

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() && matcher.groupCount() > 0){
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }
}

package com.lohika.myazin.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by amyazin on 2/18/2017.
 */
public class GridViewCell extends AbstractElement {

    public GridViewCell(WebElement decoratedElement) {
        super(decoratedElement);
    }

    public String getValue(){
        return decoratedElement.getText();
    }

    public boolean isChecked() {
        List<WebElement> checkbox = decoratedElement.findElements(By.cssSelector("input[type='checkbox']"));
        return checkbox.size() > 0 ? checkbox.get(0).isSelected() : false;
    }
}

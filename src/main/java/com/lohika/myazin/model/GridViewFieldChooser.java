package com.lohika.myazin.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/13/2017.
 */
public class GridViewFieldChooser extends BaseWebElement{
    public String fieldName;

    public GridViewFieldChooser(final WebElement element) {
        super(element);
        fieldName = getName();
    }

    public boolean isSelected(){
        return decoratedElement.getAttribute("class").contains("selected");
    }

    public void check(){
        if (!isSelected())
            switchCheckbox();
    }

    public void uncheck(){
        if (isSelected())
            switchCheckbox();
    }

    public void switchCheckbox(){
        decoratedElement.findElement(By.xpath(".//div[@data-aid='alm-checkbox']")).click();
    }

    private String getName(){
        return decoratedElement.findElement(By.xpath(".//label/span")).getText().trim();
    }
}

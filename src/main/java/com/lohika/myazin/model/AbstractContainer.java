package com.lohika.myazin.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

/**
 * Created by amyazin on 2/15/2017.
 */
public abstract class AbstractContainer implements IContainer {
    protected WebElement containedElement;
    protected WebDriver driver;
    protected Object parent;

    public AbstractContainer(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void init(WebElement containedElement, Object parent) {
        this.containedElement = containedElement;
        this.parent = parent;
    }

    @Override
    public void init(WebElement containedElement) {
        this.containedElement = containedElement;
    }

    @Override
    public Object getParent() {
        return parent;
    }

    public WebElement getContainedElement(){
        return containedElement;
    }

    public boolean isDisplayed(){
        return containedElement.isDisplayed();
    }
}

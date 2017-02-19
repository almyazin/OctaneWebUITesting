package com.lohika.myazin.model;

import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/14/2017.
 */
abstract class AbstractElement implements IElement {
    protected final WebElement decoratedElement;

    public AbstractElement(final WebElement decoratedElement){
        this.decoratedElement = decoratedElement;
    }
}

package com.lohika.myazin.model;

import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/13/2017.
 */
public class BaseWebElement extends AbstractElement {

    public BaseWebElement(final WebElement decoratedElement) {
        super(decoratedElement);
    }
}

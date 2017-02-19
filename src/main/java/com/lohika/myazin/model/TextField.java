package com.lohika.myazin.model;

import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/14/2017.
 */
public class TextField extends BaseWebElement {
    public TextField(final WebElement decoratedElement) {
        super(decoratedElement);
    }

    public void clear(){
        decoratedElement.clear();
    }

    public void type(final String s){
        decoratedElement.sendKeys(s);
    }

    public void sendKeys(CharSequence... var1){
        decoratedElement.sendKeys(var1);
    }

    public void clearAndType(final String s){
        decoratedElement.clear();
        decoratedElement.sendKeys(s);
    }
}

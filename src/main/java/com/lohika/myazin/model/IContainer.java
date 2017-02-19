package com.lohika.myazin.model;

import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/15/2017.
 */
public interface IContainer {
    void init(WebElement containedElement);

    void init(WebElement proxy, Object parent);

    Object getParent();
}

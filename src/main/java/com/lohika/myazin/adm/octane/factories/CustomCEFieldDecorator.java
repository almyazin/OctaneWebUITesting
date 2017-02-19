package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.model.IContainer;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Created by amyazin on 2/18/2017.
 */
public class CustomCEFieldDecorator extends CustomFieldDecorator{
    public CustomCEFieldDecorator(SearchContext searchContext) {
        super(searchContext);
    }

    public CustomCEFieldDecorator(ElementLocatorFactory elementLocatorFactory) {
        super(elementLocatorFactory);
    }

    public CustomCEFieldDecorator(SearchContext searchContext, int timeOutInSeconds) {
        super(searchContext, timeOutInSeconds);
    }

    public CustomCEFieldDecorator(ElementLocatorFactory elementLocatorFactory, WebDriver driver) {
        super(elementLocatorFactory, driver);
    }

    public Object decorate(ClassLoader loader, Field field, Object parent) {
        this.parent = parent;
        return super.decorate(loader, field);
    }

    @Override
    protected Object decorateContainer(ClassLoader loader, ElementLocator locator, Class<IContainer> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        IContainer container = WrapperFactory.createContainer(clazz, driver);
        CustomPageFactory.initElements(new CustomCEFieldDecorator(new CustomConditionedElementLocatorFactory(proxy, timeOutInSeconds), driver), container);
        container.init(proxy, parent);
        return container;
    }
}

package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.model.IContainer;
import com.lohika.myazin.model.IElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;

import java.lang.reflect.*;
import java.util.List;

/**
 * Created by amyazin on 2/13/2017.
 */
public class CustomFieldDecorator extends DefaultFieldDecorator {
    protected Object parent;
    protected int timeOutInSeconds = 10;
    protected WebDriver driver;

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
        try {
            this.driver = (WebDriver) searchContext;
        } catch (ClassCastException e) {}
    }

    public CustomFieldDecorator(ElementLocatorFactory elementLocatorFactory){
        super(elementLocatorFactory);
    }

    public CustomFieldDecorator(SearchContext searchContext, int timeOutInSeconds) {
        super(new AjaxElementLocatorFactory(searchContext, timeOutInSeconds));
        this.timeOutInSeconds = timeOutInSeconds;
        try {
            this.driver = (WebDriver) searchContext;
        } catch (ClassCastException e) {}
    }

    public CustomFieldDecorator(ElementLocatorFactory elementLocatorFactory, WebDriver driver) {
        super(elementLocatorFactory);
        this.driver = driver;
    }

    public CustomFieldDecorator(ElementLocatorFactory elementLocatorFactory, WebDriver driver, Object parent) {
        this(elementLocatorFactory, driver);
        this.parent = parent;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class clazz = decoratedClass(field);

        if (clazz != null) {
            ElementLocator locator = factory.createLocator(field);
            if (null == locator)
                return null;

            if (IContainer.class.isAssignableFrom(clazz))
                return decorateContainer(loader, locator, clazz);

            if (List.class.isAssignableFrom(field.getType()))
                return decorateList(loader, locator, clazz);
            
            return decorateElement(loader, locator, clazz);
        }

        return super.decorate(loader, field);
    }

    protected Object decorateElement(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createElement(clazz, proxy);
    }

    protected Object decorateContainer(ClassLoader loader, ElementLocator locator, Class<IContainer> clazz){
        WebElement proxy = proxyForLocator(loader, locator);
        IContainer container = WrapperFactory.createContainer(clazz, driver);
        PageFactory.initElements(new CustomFieldDecorator(new CustomConditionedElementLocatorFactory(proxy, timeOutInSeconds), driver, container), container);
        container.init(proxy, parent);
        return container;
    }

    protected List<IElement> decorateList(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(clazz, locator);
        return (List<IElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    protected Class decoratedClass(Field field){
        Class<?> clazz = field.getType();

        if (IContainer.class.isAssignableFrom(clazz))
            return clazz;

        if (List.class.isAssignableFrom(clazz)){
            if (null == field.getAnnotation(FindBy.class) && null == field.getAnnotation(FindBys.class))
                return null;

            Type genericType = field.getGenericType();
            if (! (genericType instanceof ParameterizedType))
                return null;

            clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }

        if (IElement.class.isAssignableFrom(clazz))
            return (Class<IElement>) clazz;

        return null;
    }
}

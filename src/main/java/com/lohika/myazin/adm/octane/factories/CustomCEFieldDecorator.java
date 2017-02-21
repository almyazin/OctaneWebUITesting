package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.model.IContainer;
import com.lohika.myazin.model.IElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.*;
import java.util.List;

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
        Class clazz = decoratedClass(field);
        if (clazz != null) {
            ElementLocator locator = factory.createLocator(field);
            if (null == locator)
                return null;
            if (List.class.isAssignableFrom(field.getType()) && IContainer.class.isAssignableFrom(clazz))
                return decorateContainersList(loader, locator, clazz);
        }

        return super.decorate(loader, field);
    }

    @Override
    protected Class decoratedClass(Field field) {
        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)){
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType)
                if (field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null) {
                    clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
                    if (IContainer.class.isAssignableFrom(clazz))
                        return (Class<IContainer>) clazz;
                }
        }
        return super.decoratedClass(field);
    }

    @Override
    protected Object decorateContainer(ClassLoader loader, ElementLocator locator, Class<IContainer> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        IContainer container = WrapperFactory.createContainer(clazz, driver);
        CustomPageFactory.initElements(new CustomCEFieldDecorator(new CustomConditionedElementLocatorFactory(proxy, timeOutInSeconds), driver), container);
        container.init(proxy, parent);
        return container;
    }

    protected List<IContainer> decorateContainersList(ClassLoader loader, ElementLocator locator, Class<IContainer> clazz){
        InvocationHandler handler = new LocatingCustomContainerListHandler(clazz, locator, parent, driver);
        return (List<IContainer>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}

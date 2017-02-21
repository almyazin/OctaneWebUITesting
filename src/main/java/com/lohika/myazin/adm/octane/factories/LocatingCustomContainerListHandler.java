package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.adm.octane.pages.IContainedPage;
import com.lohika.myazin.model.IContainer;
import com.lohika.myazin.model.IElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyazin on 2/19/2017.
 */
public class LocatingCustomContainerListHandler implements InvocationHandler {
    private Class<IContainer> decoratedContainer;
    private ElementLocator locator;
    private Object parent;
    private WebDriver driver;
    private int timeOutInSeconds = 10;

    public LocatingCustomContainerListHandler(Class<IContainer> decoratedContainer, ElementLocator locator, Object parent, WebDriver driver) {
        this.decoratedContainer = decoratedContainer;
        this.locator = locator;
        this.parent = parent;
        this.driver = driver;
    }

    public LocatingCustomContainerListHandler(Class<IContainer> decoratedContainer, ElementLocator locator, Object parent, WebDriver driver, int timeOutInSeconds) {
        this(decoratedContainer, locator, parent, driver);
        this.timeOutInSeconds = timeOutInSeconds;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<WebElement> proxyList = locator.findElements();
        ArrayList<IContainer> list = new ArrayList<IContainer>();

        for (WebElement element : proxyList) {
            IContainer container = WrapperFactory.createContainer(decoratedContainer, driver);
            CustomPageFactory.initElements(new CustomCEFieldDecorator(new CustomConditionedElementLocatorFactory(element, timeOutInSeconds), driver), container);
            container.init(element, parent);
            list.add(container);
        }
        try {
            return method.invoke(list, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}

package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.model.IElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amyazin on 2/14/2017.
 */
public class LocatingCustomElementListHandler implements InvocationHandler {
    private Class<IElement> decoratedElement;
    private ElementLocator locator;

    public LocatingCustomElementListHandler(Class<IElement> decoratedElement, ElementLocator locator) {
        this.decoratedElement = decoratedElement;
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<WebElement> proxyList = locator.findElements();
        List<IElement> list = new ArrayList<IElement>();

        for (WebElement element : proxyList) {
            list.add(WrapperFactory.createElement(decoratedElement, element));
        }
        try {
            return method.invoke(list, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }

    }
}

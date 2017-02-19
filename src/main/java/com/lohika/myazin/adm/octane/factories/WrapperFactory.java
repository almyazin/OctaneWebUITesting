package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.model.IContainer;
import com.lohika.myazin.model.IElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by amyazin on 2/14/2017.
 */
public class WrapperFactory {
    public static IElement createElement(Class<IElement> clazz, WebElement decoratedElement){
        try {
            return clazz.getConstructor(WebElement.class).newInstance(decoratedElement);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static IContainer createContainer(Class<IContainer> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static IContainer createContainer(Class<IContainer> clazz, WebDriver driver) {
        try {
            return clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            return createContainer(clazz);
        }
    }
}

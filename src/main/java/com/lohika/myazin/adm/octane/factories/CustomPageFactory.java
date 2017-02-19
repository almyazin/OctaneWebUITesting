package com.lohika.myazin.adm.octane.factories;

import com.sun.istack.internal.NotNull;
import org.jetbrains.annotations.Contract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by amyazin on 2/18/2017.
 */
public class CustomPageFactory extends PageFactory {
    @Contract(pure = true)
    public CustomPageFactory() {
    }

    public static <T> T initElements(WebDriver driver, @NotNull Class<T> pageClassToProxy) {
        Object page = instantiatePage(driver, pageClassToProxy);
        initElements(driver, page);
        return (T) page;
    }

    public static void initElements(WebDriver driver, @NotNull Object page) {
        initElements((ElementLocatorFactory)(new DefaultElementLocatorFactory(driver)), (Object)page);
    }

    public static void initElements(ElementLocatorFactory factory, @NotNull Object page) {
        initElements((FieldDecorator)(new DefaultFieldDecorator(factory)), (Object)page);
    }

    public static void initElements(FieldDecorator decorator, @NotNull Object page) {
        for(Class proxyIn = page.getClass(); proxyIn != Object.class; proxyIn = proxyIn.getSuperclass()) {
            proxyFields((CustomCEFieldDecorator) decorator, page, proxyIn);
        }

    }

    private static void proxyFields(CustomCEFieldDecorator decorator, Object page, @NotNull Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            Object value = decorator.decorate(page.getClass().getClassLoader(), field, page);
            if(value != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException var10) {
                    throw new RuntimeException(var10);
                }
            }
        }

    }

    private static <T> T instantiatePage(WebDriver driver, @NotNull Class<T> pageClassToProxy) {
        try {
            try {
                Constructor e = pageClassToProxy.getConstructor(new Class[]{WebDriver.class});
                return (T) e.newInstance(new Object[]{driver});
            } catch (NoSuchMethodException var3) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException var4) {
            throw new RuntimeException(var4);
        } catch (IllegalAccessException var5) {
            throw new RuntimeException(var5);
        } catch (InvocationTargetException var6) {
            throw new RuntimeException(var6);
        }
    }
}

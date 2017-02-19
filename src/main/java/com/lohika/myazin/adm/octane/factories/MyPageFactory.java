package com.lohika.myazin.adm.octane.factories;

import com.lohika.myazin.adm.octane.pages.Page;
import com.lohika.myazin.model.IContainer;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyPageFactory {

  public static <T extends Page> T getPage(WebDriver driver, Class<T> pageClass) {
    return getPage(driver, pageClass, 10);
  }

  public static <T extends Page> T getPage(WebDriver driver, Class<T> pageClass, int timeOutInSeconds) {
    T page = instantiatePage(driver, pageClass);
    //PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOutInSeconds), page);
    //PageFactory.initElements(new CustomFieldDecorator(driver, timeOutInSeconds), page);
    /*PageFactory.initElements(new CustomFieldDecorator(
            new CustomConditionedElementLocatorFactory(driver, timeOutInSeconds), driver, page), page);*/
    CustomPageFactory.initElements(new CustomCEFieldDecorator(
            new CustomConditionedElementLocatorFactory(driver, timeOutInSeconds), driver), page);
    page.driver = driver;
    if (! page.isOnThisPage()) {
      page.tryToOpen();
      if (! page.isOnThisPage()) {
        throw new Error("Can't open page " + pageClass);
      }
    }
    return page;
  }

  private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
    try {
      try {
        Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
        return constructor.newInstance(driver);
      } catch (NoSuchMethodException e) {
        return pageClassToProxy.newInstance();
      }
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}

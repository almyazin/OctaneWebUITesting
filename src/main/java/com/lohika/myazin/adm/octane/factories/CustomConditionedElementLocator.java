package com.lohika.myazin.adm.octane.factories;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;

import java.lang.reflect.Field;

/**
 * Created by amyazin on 2/14/2017.
 */
public class CustomConditionedElementLocator extends AjaxElementLocator {
    private final UsabilityCondition condition;
    public CustomConditionedElementLocator(SearchContext searchContext, Field field,
                                           int timeOutInSeconds) {
        super(searchContext, field, timeOutInSeconds);
        condition = UsabilityCondition.PRESENT;
    }

    public CustomConditionedElementLocator(SearchContext searchContext, Field field,
                                           int timeOutInSeconds, final UsabilityCondition condition){
        super(searchContext, field, timeOutInSeconds);
        this.condition = condition;
    }

    @Override
    protected boolean isElementUsable(WebElement element) {
        switch (condition){
            case VISIBLE:
                return element.isDisplayed();
            case ENABLED:
                return element.isEnabled();
            case PRESENT:
            default:
                return super.isElementUsable(element);
        }
    }
}

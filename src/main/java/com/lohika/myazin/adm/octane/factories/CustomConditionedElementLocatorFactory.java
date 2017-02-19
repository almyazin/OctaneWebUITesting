package com.lohika.myazin.adm.octane.factories;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by amyazin on 2/14/2017.
 */
public class CustomConditionedElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;
    private final int timeOutInSeconds;

    public CustomConditionedElementLocatorFactory(SearchContext searchContext, int timeOutInSeconds){
        this.searchContext = searchContext;
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public ElementLocator createLocator(Field field, UsabilityCondition condition) {
        return new CustomConditionedElementLocator(searchContext, field, timeOutInSeconds, condition);
    }

    @Override
    public ElementLocator createLocator(Field field) {
        UsabilityCondition condition = getUsabilityCondition(field);
        if (condition != UsabilityCondition.PRESENT)
            return new CustomConditionedElementLocator(searchContext, field, timeOutInSeconds, condition);

        return new CustomConditionedElementLocator(searchContext, field, timeOutInSeconds);
    }

    private UsabilityCondition getUsabilityCondition(final Field field){
        WaitForCondition wfc;
        if ((wfc = field.getAnnotation(WaitForCondition.class)) != null){
            return wfc.value();
        } else if (field.getAnnotation(VisibilityRquired.class) != null)
            return UsabilityCondition.VISIBLE;
        else if (field.getAnnotation(EnablingRequired.class) != null)
            return UsabilityCondition.ENABLED;

        return UsabilityCondition.PRESENT;
    }
}

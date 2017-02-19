package com.lohika.myazin.adm.octane.factories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amyazin on 2/16/2017.
 */

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablingRequired {
    UsabilityCondition value() default UsabilityCondition.ENABLED;
}

package com.lohika.myazin.octane.testdata;

import java.util.Collection;
import java.util.List;

/**
 * Created by amyazin on 2/16/2017.
 */
public abstract class BaseTestData {
    protected List<Object[]> list;

    public Collection<Object[]> getData() {
        return list;
    }
}

package com.lohika.myazin.adm.octane.pages;

/**
 * Created by amyazin on 2/18/2017.
 */
public abstract class AbstractContainedPage extends Page implements IContainedPage {
    protected Page parentPage;

    @Override
    public Page getParentPage() {
        return parentPage;
    }
}

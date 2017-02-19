package com.lohika.myazin.adm.octane.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by amyazin on 2/10/2017.
 */
public abstract class Page {
    public WebDriver driver;

    public boolean isOnThisPage() {
        return true;
    }

    public Page sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public abstract void tryToOpen();
}

package com.lohika.myazin.adm.octane.pages;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by amyazin on 2/12/2017.
 */
public class EntitiesAreaEnabledPage extends InternalPage {
    public EntitiesFromContentArea entitiesArea;

    public EntitiesAreaEnabledPage(WebDriver driver) {
        entitiesArea = MyPageFactory.getPage(driver, EntitiesFromContentArea.class);
        entitiesArea.parentPage = this;
    }

    /*public void openGridViewFieldChooser() {
        entitiesArea.openGridViewFieldChooser();
    }*/
}

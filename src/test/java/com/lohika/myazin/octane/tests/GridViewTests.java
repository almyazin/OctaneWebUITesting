package com.lohika.myazin.octane.tests;

import com.lohika.myazin.adm.octane.pages.DefectsPage;
import com.lohika.myazin.model.EntitiesGridViewContainer;
import org.junit.Test;

/**
 * Created by amyazin on 2/19/2017.
 */
public class GridViewTests extends BaseTest {

    @Test
    public void GridViewTest(){
        DefectsPage defectsPage = goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage();
        EntitiesGridViewContainer gridViewContainer = defectsPage.entitiesArea.switchToGridView()
                .entitiesArea
                .entitiesGridViewContainer;
        gridViewContainer.gridViewRows.get(0)
                .cells.get(0).getValue();
    }
}

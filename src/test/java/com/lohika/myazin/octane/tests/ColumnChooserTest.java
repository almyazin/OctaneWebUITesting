package com.lohika.myazin.octane.tests;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import com.lohika.myazin.adm.octane.pages.DefectsPage;
import com.lohika.myazin.model.GridViewFieldChooser;
import com.lohika.myazin.model.ListOf;
import com.lohika.myazin.octane.testdata.ColumnChooserTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by amyazin on 2/16/2017.
 */

@RunWith(Parameterized.class)
public class ColumnChooserTest extends BaseTest {

    public String column;

    private ListOf<String> initiallyChosenFields = new ListOf<>();

    public ColumnChooserTest(String column) {
        this.column = column;
    }

    @Parameters(name = "Show -> Hide column {0}")
    public static Collection<Object[]> columnChooserTestData(){
        ColumnChooserTestData provider = new ColumnChooserTestData();
        provider.populateData();
        return provider.getData();
    }

    @Test
    public void GridViewFieldsChooserContainerChooseFieldsTest() throws InterruptedException {
        DefectsPage defectsPage = goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage();

        initiallyChosenFields.forEach(System.out::println);
        defectsPage.entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.checkGridFieldChooser(column);
        assertTrue(String.format("Column: %s was not choosen", column), defectsPage.entitiesArea
            ._gridViewFieldChooserContainer.getGridFieldChooser(column).isSelected());
        Thread.sleep(5000);

        assertNotNull(String.format("Column: %s is not displayed", column), defectsPage.entitiesArea
                .entitiesGridViewContainer.getGridViewColumnHeader(column));
        defectsPage.entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.uncheckGridFieldChooser(column);
        Thread.sleep(5000);

        restoreInitialColumnsState();
        defectsPage.logout();
    }

    private void restoreInitialColumnsState() {
        MyPageFactory.getPage(driver, DefectsPage.class).entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .stream().filter(c -> !initiallyChosenFields.contains(c.fieldName))
                .forEach(GridViewFieldChooser::uncheck);
    }

    private  void saveInitialColumnsState(){

        initiallyChosenFields.addAll(MyPageFactory.getPage(driver, DefectsPage.class).entitiesArea.switchToGridView()
                .entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .stream().filter(c -> c.isSelected()).collect(Collectors.mapping(c -> c.fieldName, Collectors.toList())));
    }
}

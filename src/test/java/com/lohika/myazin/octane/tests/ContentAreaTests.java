package com.lohika.myazin.octane.tests;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import com.lohika.myazin.adm.octane.pages.DefectsPage;
import com.lohika.myazin.model.GridViewFieldChooser;
import com.lohika.myazin.model.ListOf;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by amyazin on 2/12/2017.
 */
public class ContentAreaTests extends BaseTest {
    @Test
    public void ContentAreaTest() throws InterruptedException {
        DefectsPage defectPage = goToMainPage()
                .login("amyazin@lohika.com", "W3lcome1")
                .gotoDefectsPage();
        Thread.sleep(3000);
        defectPage.entitiesArea.switchToGridView();
        Thread.sleep(3000);
        assertTrue(defectPage.entitiesArea.isInGridView());
        defectPage.entitiesArea.switchToSmartListView();
        Thread.sleep(3000);
        assertTrue(defectPage.entitiesArea.isInSmartListView());
        defectPage.entitiesArea.switchToGridView();
        Thread.sleep(3000);
        assertTrue(defectPage.entitiesArea.isInGridView());
        defectPage.logout();
    }

    @Test
    public void GridViewHeadersTest(){
        goToMainPage()
                .login("amyazin@lohika.com", "W3lcome1")
                .gotoDefectsPage()
                .entitiesArea.switchToGridView()
                .entitiesArea.entitiesGridViewContainer.gridViewColumnsHeaders
                .forEach(gridViewHeader -> System.out.printf("%s : %b : %s\r\n", gridViewHeader.name, gridViewHeader.isColumnSorted(), gridViewHeader.getColumnSortingOrder()));
    }

    /*@Test
    public void GridViewFieldChooserTest(){
        String fieldName = goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage()
                .entitiesArea.switchToGridView()
                .entitiesArea.openGridViewFieldChooser()
                .entitiesArea.gridFieldChooser.get(0).fieldName;

        System.out.println(fieldName);
    }*/

    /*@Test
    public void GridViewFieldChooserChooseFieldsTest(){
        ListOf<String> initiallyChosenFields = new ListOf<>();
        goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage()
                .entitiesArea.switchToGridView()
                .entitiesArea.openGridViewFieldChooser()
                .entitiesArea.gridFieldChooser.forEach(c -> {if (c.isSelected()) initiallyChosenFields.add(c.fieldName);});

        initiallyChosenFields.forEach(c -> System.out.println(c));
        MyPageFactory.getPage(driver, DefectsPage.class)
                .entitiesArea.closeGridViewFieldChooser()
                .logout();
    }*/

    @Test
    public void GridViewFieldChooserContainerTest(){
        ListOf<String> initiallyChosenFields = new ListOf<>();
        goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage()
                .entitiesArea.switchToGridView()
                .entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .forEach(c -> {if (c.isSelected()) initiallyChosenFields.add(c.fieldName);});

        initiallyChosenFields.forEach(c -> System.out.println(c));
                /*.entitiesArea.closeGridViewFieldChooser()
                .logout();*/
        MyPageFactory.getPage(driver, DefectsPage.class)
                .entitiesArea._gridViewFieldChooserContainer.close()
                .logout();
    }

    @Test
    public void GridViewFieldsChooserContainerChooseFieldsTest() throws InterruptedException {
        ListOf<String> initiallyChosenFields = new ListOf<>();
        DefectsPage defectsPage = goToMainPage()
                .login("alexander.myazin@hpe.com", "DarkPhoenix01201212")
                .gotoDefectsPage();
        initiallyChosenFields.addAll(defectsPage.entitiesArea.switchToGridView()
                .entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .stream().filter(c -> c.isSelected()).collect(Collectors.mapping(c -> c.fieldName, Collectors.toList())));
                //.forEach(c -> {if (c.isSelected()) initiallyChosenFields.add(c.fieldName);});
        initiallyChosenFields.forEach(System.out::println);
        defectsPage.entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .forEach(GridViewFieldChooser::check);
        defectsPage.entitiesArea._gridViewFieldChooserContainer.open()
                .entitiesArea._gridViewFieldChooserContainer.gridFieldChooser
                .stream().filter(c -> !initiallyChosenFields.contains(c.fieldName))
                .forEach(GridViewFieldChooser::uncheck);
        Thread.sleep(5000);
        defectsPage.logout();
    }
}

package com.lohika.myazin.octane.tests;

import org.junit.Test;

/**
 * Created by amyazin on 2/11/2017.
 */
public class NavigationTests extends BaseTest {
    @Test
    public void NavigationTest(){
        goToMainPage()
                .login("amyazin@lohika.com", "W3lcome1")
                .gotoDefectsPage()
                .gotoQualityPage()
                .gotoHomePage()
                .logout();
    }
}

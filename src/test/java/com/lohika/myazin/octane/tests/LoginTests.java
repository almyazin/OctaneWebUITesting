package com.lohika.myazin.octane.tests;

import org.junit.Test;

/**
 * Created by amyazin on 2/11/2017.
 */
public class LoginTests extends BaseTest{


    @Test
    public void LoginTest(){
        goToMainPage()
                .login("amyazin@lohika.com", "W3lcome1")
                .logout();


    }
}

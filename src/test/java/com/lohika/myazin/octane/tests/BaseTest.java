package com.lohika.myazin.octane.tests;

import com.lohika.myazin.adm.octane.pages.LoginPage;
import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import com.lohika.myazin.octane.factory.WebDriverFactory;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by amyazin on 2/11/2017.
 */
public class BaseTest {
    WebDriver driver;
    String baseUrl;

    @Before
    public void SetUp(){
        //baseUrl = "http://16.60.171.130:55460";
        baseUrl = "https://mqast010pngx.saas.hpe.com";
        //driver = WebDriverFactory.getDriver(DesiredCapabilities.chrome());
        System.setProperty("webdriver.chrome.driver", "d:\\TEMP\\JavaLearn\\adm\\drivers\\chromedriver.exe");

        driver = WebDriverFactory.getDriver(DesiredCapabilities.chrome());
        driver.manage().window().maximize();
    }

    protected LoginPage goToMainPage() {
        driver.get(baseUrl);
        return MyPageFactory.getPage(driver, LoginPage.class);
    }
}

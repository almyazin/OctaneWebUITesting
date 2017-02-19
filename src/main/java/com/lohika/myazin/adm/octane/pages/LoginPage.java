package com.lohika.myazin.adm.octane.pages;

import com.lohika.myazin.adm.octane.factories.MyPageFactory;
import com.lohika.myazin.model.IElement;
import com.lohika.myazin.model.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by amyazin on 2/10/2017.
 */
public class LoginPage extends AnyPage{

    @FindBy(xpath = "//button[@id='submit_button' or @data-aid='login-app-buttons-authenticate']/ancestor::form")
    public WebElement loginForm;

    /*@FindBy(xpath = "//input[@id='username' or @id='inputUsername']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='password' or @id='inputPassword']")
    public WebElement passwordField;*/

    @FindBy(xpath = "//input[@id='username' or @id='inputUsername']")
    public TextField emailField;

    @FindBy(xpath = "//input[@id='password' or @id='inputPassword']")
    public TextField passwordField;

    @FindBy(xpath = "//button[@id='submit_button' or @data-aid='login-app-buttons-authenticate']")
    public WebElement loginButton;

    @Override
    public void tryToOpen() {
        MyPageFactory.getPage(driver, InternalPage.class).logout();
    }

    @Override
    public boolean isOnThisPage() {
        return loginForm.isDisplayed();
    }

    public InternalPage login() {
        return login("", "");
    }

    public InternalPage login(String email, String password) {

        /*emailField.clear();
        emailField.sendKeys(email);*/
        emailField.clearAndType(email);
        passwordField.clearAndType(password);
        loginButton.click();

        return MyPageFactory.getPage(driver, InternalPage.class, 30);
    }
}

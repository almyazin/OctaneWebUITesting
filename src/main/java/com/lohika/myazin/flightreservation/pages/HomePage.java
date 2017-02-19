package com.lohika.myazin.flightreservation.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends InternalPage {
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.welcomeMessage.isDisplayed();
	}
	
}

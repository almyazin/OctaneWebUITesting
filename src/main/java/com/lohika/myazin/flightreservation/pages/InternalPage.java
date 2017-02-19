package com.lohika.myazin.flightreservation.pages;

import com.lohika.myazin.flightreservation.pages.frames.LeftNavigationFrame;
import com.lohika.myazin.flightreservation.pages.frames.MainInfoFrame;
import org.openqa.selenium.WebDriver;

public class InternalPage extends AnyPage {
	
	protected LeftNavigationFrame navigationFrame;
	protected MainInfoFrame infoFrame;
	
	public InternalPage(WebDriver driver) {
		navigationFrame = MyPageFactory.getPage(driver, LeftNavigationFrame.class);
		infoFrame = MyPageFactory.getPage(driver, MainInfoFrame.class);
	}
	
	public void logout() {
		navigationFrame.switchToThis();
		navigationFrame.signOffButton.click();
	}
	
	public boolean isOnThisPage() {
		navigationFrame.switchToThis();
		return navigationFrame.signOffButton.isDisplayed();
	}

	@Override
	protected void tryToOpen() {
		MyPageFactory.getPage(driver, LoginPage.class).loginAs("jojo", "bean");
	}
	
	public void goToFindFlightsPage() {
		navigationFrame.switchToThis();
		navigationFrame.flightsButton.click();
	}
	
	public void goToHomePage() {
		navigationFrame.switchToThis();
		navigationFrame.homeButton.click();
	}
	public void goToItinerary() {
		navigationFrame.switchToThis();
		navigationFrame.itineraryButton.click();
	}
}

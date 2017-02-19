package com.lohika.myazin.flightreservation.tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lohika.myazin.flightreservation.pages.FindFlightsPage;
import com.lohika.myazin.flightreservation.pages.InternalPage;
import com.lohika.myazin.flightreservation.pages.InvoicePage;
import com.lohika.myazin.flightreservation.pages.ItineraryPage;
import com.lohika.myazin.flightreservation.pages.LoginPage;
import com.lohika.myazin.flightreservation.pages.MyPageFactory;
import com.lohika.myazin.flightreservation.pages.PaymentDetailsPage;
import com.lohika.myazin.flightreservation.pages.SelectFlightPage;

public class BookFlightTest {
	public WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "d:\\TEMP\\JavaLearn\\adm\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = "http://localhost:1080/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	private void goToMainPage() {
		// open main page
		driver.get(baseUrl + "/WebTours/");
	}
	
	@Test
	public void bookFlightTest() {
		//goToMainPage();
		
		//MyPageFactory.getPage(driver, LoginPage.class).loginAs("jojo", "bean");
		
		MyPageFactory.getPage(driver, InternalPage.class).goToFindFlightsPage();
		MyPageFactory.getPage(driver, FindFlightsPage.class).expandDepartureList();
		MyPageFactory.getPage(driver, FindFlightsPage.class).collapseDepartureList();
		MyPageFactory.getPage(driver, FindFlightsPage.class).expandArrivalList();
		MyPageFactory.getPage(driver, FindFlightsPage.class).collapseArrivalList();
		MyPageFactory.getPage(driver, FindFlightsPage.class).fillFindFlightForm();
		
		MyPageFactory.getPage(driver, SelectFlightPage.class).selectFlight();
		MyPageFactory.getPage(driver, PaymentDetailsPage.class).fillPaymentDetails();
		assertTrue("Not on the Invoice Page", MyPageFactory.getPage(driver, InvoicePage.class).isOnThisPage());
		
		MyPageFactory.getPage(driver, InternalPage.class).goToItinerary();
		assertTrue("There're no flights", MyPageFactory.getPage(driver, ItineraryPage.class).areThereScheduledFlights());
		
		MyPageFactory.getPage(driver, ItineraryPage.class).cancelAllFlights();
		
		MyPageFactory.getPage(driver, InternalPage.class).logout();
	}
	
	@After
	public void tearDown() {
		
		driver.close();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

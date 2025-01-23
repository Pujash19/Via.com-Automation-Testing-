package com.via.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.via.base.ViaBase;
import com.via.pages.EnterDetailsPage;
import com.via.pages.FlightBookPage;
import com.via.pages.FlightPage;
import com.via.pages.HotelBookPage;
import com.via.pages.HotelPage;
import com.via.pages.SignInPage;

public class EnterDetailsPageTest {
	SignInPage sp;
	FlightPage fp;
	FlightBookPage fb;
	EnterDetailsPage ed;
	HotelPage hp;
	HotelBookPage hb;

	@BeforeTest
	public void reportConfiguration() {
		ViaBase.configureExtentReports();
	}

	@AfterTest
	public void publishReport() {
		ViaBase.generateReports();
	}

	@BeforeMethod
	public void setup() {
		sp = new SignInPage();
		sp.initialization();
	}

	@AfterMethod
	public void closeSetup() {

		sp.tearDown();
	}

	@Test
	public void validateOneWayDetails() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating One way flight user details of page");
		fp = new FlightPage();
		sp.signIn();
		fb = fp.oneWay();
		fb = new FlightBookPage();
		ed = fb.searchOneWayFlight();
		String actText = ed.enterFlightDetails();
		Assert.assertEquals(actText, "Pay Now");
		test.log(Status.PASS, "One way flight user details test case pass");
	}

	@Test(priority = 1)
	public void validateRoundTripDetails() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Round Trip flight user details of page");
		fp = new FlightPage();
		sp.signIn();
		fb = fp.roundTrip();
		fb = new FlightBookPage();
		ed = fb.searchRoundTripFlight();
		String actText = ed.enterFlightDetails();
		Assert.assertEquals(actText, "Pay Now");
		test.log(Status.PASS, "Round Trip flight user details test case pass");
	}

	@Test(priority = 2)
	public void validateMultiCityDetails() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Multi City flight user details of page");
		fp = new FlightPage();
		sp.signIn();
		fb = fp.multiCity();
		fb = new FlightBookPage();
		ed = fb.searchMultiCityFlight();
		String actText = ed.enterFlightDetails();
		Assert.assertEquals(actText, "Pay Now");
		test.log(Status.PASS, "Multi City flight user details test case pass");
	}

	@Test(priority = 3)
	public void validateHotelDetailsPageTest() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Hotel user details of page");
		sp.signIn();
		hp = new HotelPage();
		hb = hp.hotelSearch();
		Thread.sleep(3000);
		hb = new HotelBookPage();
		Thread.sleep(3000);
		ed = hb.selectHotelRoom();
		Thread.sleep(3000);
		String actValue = ed.enterHotelDetails();
		Thread.sleep(3000);
		Assert.assertTrue(actValue.contains("Pay Now"));
		test.log(Status.PASS, "Hotel user details test case pass");
	}
}

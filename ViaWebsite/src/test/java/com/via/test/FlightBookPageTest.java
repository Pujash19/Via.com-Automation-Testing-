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
import com.via.pages.SignInPage;

public class FlightBookPageTest {
	SignInPage sp;
	FlightPage fp;
	FlightBookPage fbp;
	EnterDetailsPage ed;

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
	public void validateOneWayFlightBookPage() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating One way flight booking of page");
		sp.signIn();
		fp = new FlightPage();
		fbp = fp.oneWay();
		fbp = new FlightBookPage();
		ed = fbp.searchOneWayFlight();
		String actValue = ed.validateFlightReviewPage();
		Assert.assertTrue(actValue.contains("Back to Flight Results"));
		test.log(Status.PASS, "One way flight booking test case pass");
	}

	@Test(priority = 1)
	public void ValidateRoundTripFlightBookPage() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Round trip flight booking of page");
		fp = new FlightPage();
		sp.signIn();
		fbp = fp.roundTrip();
		fbp = new FlightBookPage();
		ed = fbp.searchRoundTripFlight();
		String actValue = ed.validateFlightReviewPage();
		Assert.assertTrue(actValue.contains("Back to Flight Results"));
		test.log(Status.PASS, "Round trip flight booking test case pass");

	}

	@Test(priority = 2)
	public void ValidateMultiCityFlightBookPage() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Multi City flight booking of page");
		fp = new FlightPage();
		sp.signIn();
		fbp = fp.multiCity();
		fbp = new FlightBookPage();
		ed = fbp.searchMultiCityFlight();
		String actValue = ed.validateFlightReviewPage();
		Assert.assertTrue(actValue.contains("Back to Flight Results"));
		test.log(Status.PASS, "Multi City flight booking test case pass");

	}

}

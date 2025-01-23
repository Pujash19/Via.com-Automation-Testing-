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
import com.via.pages.FlightBookPage;
import com.via.pages.FlightPage;
import com.via.pages.SignInPage;

public class FlightPageTest {
	SignInPage sp;
	FlightPage fp;
	FlightBookPage fbp;

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
	public void validateOneWayTripTest() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating One wayTrip of page");
		fp = new FlightPage();
		sp.signIn();
		fbp = fp.oneWay();
		String actValue = fbp.validateFlightBookPage();
		Assert.assertTrue(actValue.contains("Flight Results"));
		test.log(Status.PASS, "One WayTrip test case pass");

	}

	@Test(priority = 1)
	public void validateRoundTripTest() {
		ExtentTest test = sp.extent.createTest("Validating RoundTrip of page");
		fp = new FlightPage();
		sp.signIn();
		fbp = fp.roundTrip();
		String actValue = fbp.validateFlightBookPage();
		Assert.assertTrue(actValue.contains("Flight Results"));
		test.log(Status.PASS, "Round Trip test case pass");
	}

	@Test(priority = 2)
	public void validateMultiCityTest() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating MultiCity of page");
		fp = new FlightPage();
		sp.signIn();
		fbp = fp.multiCity();
		String actValue = fbp.validateFlightBookPage();
		Assert.assertTrue(actValue.contains("Flight Results"));
		test.log(Status.PASS, "Multi City test case pass");
	}

}

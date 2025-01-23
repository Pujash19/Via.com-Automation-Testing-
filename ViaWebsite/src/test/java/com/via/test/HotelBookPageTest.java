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
import com.via.pages.HotelBookPage;
import com.via.pages.HotelPage;
import com.via.pages.SignInPage;

public class HotelBookPageTest {

	SignInPage sp;
	HotelPage hp;
	HotelBookPage hb;
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

	@Test
	public void validateHotelBookPageTest() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Hotel booking of page");
		sp.signIn();
		hp = new HotelPage();
		hb = hp.hotelSearch();
		Thread.sleep(3000);
		ed = hb.selectHotelRoom();
		String actValue = ed.validateHotelReviewPage();
		Assert.assertTrue(actValue.contains("Back to Search Results"));
		test.log(Status.PASS, "Hotel booking test case pass");
	}

	@AfterMethod
	public void closeSetup() {
		sp.tearDown();
	}

}
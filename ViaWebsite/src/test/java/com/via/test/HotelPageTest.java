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
import com.via.pages.HotelBookPage;
import com.via.pages.HotelPage;
import com.via.pages.SignInPage;

public class HotelPageTest {
	SignInPage sp;
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

	@Test
	public void validateHotelSearch() throws InterruptedException {
		ExtentTest test = sp.extent.createTest("Validating Hotel search of page");
		hp = new HotelPage();
		sp.signIn();
		hb = hp.hotelSearch();
		String actVal = hb.validateHotelBookPage();
		Thread.sleep(5000);
		Assert.assertTrue(actVal.contains("All Hotels"));
		test.log(Status.PASS, "Hotel search test case pass");
	}

	@AfterMethod
	public void closeSetup() {
		sp.tearDown();
	}
}
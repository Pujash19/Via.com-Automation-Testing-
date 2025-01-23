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
import com.via.pages.SignInPage;

public class SignInPageTest {

	SignInPage sp;

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
	public void validateSignInTest() {
		ExtentTest test = sp.extent.createTest("Validating SignIn of page");
		String actText = sp.signIn();
		String ExpectedText = "Hi Khushi";
		Assert.assertTrue(actText.contains(ExpectedText));
		test.log(Status.PASS, "SignIn test case pass");

	}
}
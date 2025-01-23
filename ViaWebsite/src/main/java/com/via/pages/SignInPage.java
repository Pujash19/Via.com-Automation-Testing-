package com.via.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.via.base.ViaBase;

public class SignInPage extends ViaBase {

	public String signIn() {

		String name = "SignInPage";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		driver.findElement(By.xpath("//*[@id=\"wzrk-cancel\"]")).click();
		WebElement signin = driver.findElement(By.xpath("//*[@id=\"SignIn\"]/div"));
		signin.click();

		driver.findElement(By.xpath("//*[@id=\"loginIdText\"]")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.xpath("//input[@id='passwordText']")).sendKeys(prop.getProperty("password"));
		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath("//input[@id='loginValidate']")).click();

		String signInText = driver.findElement(By.xpath("//div[@class= 'elementPad menuLabel secNavIcon']")).getText();
		return signInText;
	}
}
package com.via.pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.base.ViaBase;

public class EnterDetailsPage extends ViaBase {

	public String validateFlightReviewPage() throws InterruptedException {
		return driver.findElement(By.xpath("//a[@class= 'backToRes']")).getText();
	}

	public String validateHotelReviewPage() throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(By.xpath("//p[@class='backToRes']")).getText();
	}

	public String enterFlightDetails() throws InterruptedException {

		String name = "FlightUserDetails";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		WebElement title = driver.findElement(By.xpath("//select[@id='adult1Title']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Actions act = new Actions(driver);

		// details
		act.click(title).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();
		driver.findElement(By.xpath("//input[@id='adult1FirstName']")).sendKeys(prop.getProperty("first-name"));
		driver.findElement(By.xpath("//input[@id='adult1Surname']")).sendKeys(prop.getProperty("last-name"));
		driver.findElement(By.xpath("//input[@id= 'contactMobile']")).sendKeys(prop.getProperty("phone-no"));
		driver.findElement(By.xpath("//input[@id= 'contactEmail']")).sendKeys(prop.getProperty("email-id"));
		Thread.sleep(10000);

		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath("//button[@id= 'makePayCTA']")).click();
		driver.findElement(By.xpath("//label[@for= 'refundProtectRadTrue']")).click();
		driver.findElement(By.xpath("//button[@class= 'confirmPayBtn js-proceed-to-payment']")).click();
		return validateFlightPayment();
	}

	public String validateFlightPayment() {
		String name = "FlightFinalPayment";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);
		captureScreenshot(driver, newGeneratedName);
		return driver.findElement(By.xpath("//button[@id= 'paymentCTA']")).getText();
	}

	public String enterHotelDetails() throws InterruptedException {
		String name = "HotelUserDetails";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//select[@name='Room0AdultTitle0']/option[2]")).click();

		// FirstName
		driver.findElement(By.xpath("//input[@name='Room0AdultFirstName0']")).click();
		act.sendKeys(prop.getProperty("first-name")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-1']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

		// LastName
		driver.findElement(By.xpath("//div[@class='compulDet']/div[3]/input[@name='Room0AdultLastName0']")).click();
		act.sendKeys(prop.getProperty("last-name")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-2']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

		// Email
		driver.findElement(By.xpath("//input[@id='contactMobile']")).click();
		act.sendKeys(prop.getProperty("phone-no")).keyDown(Keys.TAB).keyUp(Keys.TAB)
				.sendKeys(prop.getProperty("email-id")).perform();

		captureScreenshot(driver, newGeneratedName);
		// checkBox
		driver.findElement(By.xpath("//label[@id='read_terms_label']")).click();
		// ProceedToBooking
		driver.findElement(By.xpath("//button[@id='makePayCTA']")).click();
		// MakePayment
		driver.findElement(By.xpath("//div[@class='paxDiv topOnlyBorder']/div/div/div[2]/button")).click();
		return validateHotelPayment();
	}

	public String validateHotelPayment() {
		String name = "HotelFinalPayment";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		captureScreenshot(driver, newGeneratedName);
		return driver.findElement(By.xpath("//button[@id='paymentCTA']")).getText();
	}

}

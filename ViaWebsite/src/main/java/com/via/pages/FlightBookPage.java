package com.via.pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.base.ViaBase;

public class FlightBookPage extends ViaBase {

	public String validateFlightBookPage() {
		return driver.findElement(By.xpath("//div[@class='resCount']")).getText();
	}

	public EnterDetailsPage searchOneWayFlight() throws InterruptedException {
		Thread.sleep(5000);
		String name = "OneWayFlight";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath("//button[@class='bookCTA u_marB5']")).click();
		return new EnterDetailsPage();
	}

	public EnterDetailsPage searchRoundTripFlight() throws InterruptedException {

		String name = "RoundTripFlight";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		Thread.sleep(6000);
		driver.findElement(
				By.xpath("//div[@class= 'onwardResults']/div[3]/div/div[2]/div[2]/button[@class= 'bookCTA u_marB5']"))
				.click();
		driver.findElement(
				By.xpath("//div[@class= 'returnResults']/div[3]/div/div[2]/div[2]/button[@class= 'bookCTA u_marB5']"))
				.click();
		captureScreenshot(driver, newGeneratedName);

		driver.findElement(By.xpath("//div[text()= 'Book Flights']")).click();
		return new EnterDetailsPage();

	}

	public EnterDetailsPage searchMultiCityFlight() throws InterruptedException {
		String name = "MultiCityFlight";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		Thread.sleep(5000);
		driver.findElement(
				By.xpath("//div[@class='onwardResults']/div[3]/div/div[2]/div[2]/button[@class= 'bookCTA u_marB5']"))
				.click();
		driver.findElement(
				By.xpath("//div[@class='returnResults']/div[3]/div/div[2]/div[2]/button[@class= 'bookCTA u_marB5']"))
				.click();

		captureScreenshot(driver, newGeneratedName);

		driver.findElement(By.xpath("//div[text()= 'Book Flights']")).click();
		return new EnterDetailsPage();
	}

}

package com.via.pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.base.ViaBase;

public class FlightPage extends ViaBase {

	public FlightBookPage oneWay() throws InterruptedException {

		String name = "OneWayPage";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		WebElement target = driver.findElement(By.xpath("//input[@id='source']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions act = new Actions(driver);

		// city
		act.moveToElement(target).click().sendKeys(prop.getProperty("Departure-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-1']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).sendKeys(prop.getProperty("Arrival-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-2']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();

		// date
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//div[@class='vc-cell vc-selected-cell']"))))
				.click();
		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath("//div[@id='search-flight-btn']")).click();
		return new FlightBookPage();
	}

	public FlightBookPage roundTrip() {

		String name = "RoundTripPage";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		driver.findElement(By.xpath("//div[@class= 'round-trip']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement source = driver.findElement(By.xpath("//input[@id= 'source']"));
		WebElement destination = driver.findElement(By.xpath("//input[@id= 'destination']"));

		Actions act = new Actions(driver);

		// city

		act.click(source).sendKeys(prop.getProperty("Departure-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id= 'ui-id-1']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();

		act.click(destination).sendKeys(prop.getProperty("Arrival-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id= 'ui-id-2']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();

		// date

		WebElement departureDate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='vc-cell vc-selected-cell']")));

		if (departureDate.isDisplayed() && departureDate.isEnabled()) {
			departureDate.click();
		}

		driver.findElement(By.xpath("//input[@id='return']")).click();

		WebElement ReturnDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@id= 'return-cal']/child::div[@class='vc-month-box-container'][@data-month='11']/child::div[@class='vc-month-box']/div[4]/child::div[@class='vc-cell '][@data-date='16']")));

		if (ReturnDate.isDisplayed() && ReturnDate.isEnabled()) {
			ReturnDate.click();
		}
		captureScreenshot(driver, newGeneratedName);

		driver.findElement(By.xpath("//div[@id= 'search-flight-btn']")).click();
		return new FlightBookPage();
	}

	public FlightBookPage multiCity() throws InterruptedException {

		String name = "MultiCityPage";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		driver.findElement(By.xpath("//label[@for='multi-city']")).click();
		WebElement target = driver.findElement(By.xpath("//input[@id='source-0']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions act = new Actions(driver);

		// city
		act.moveToElement(target).click().sendKeys(prop.getProperty("Departure-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-3']"))))
				.click();
		WebElement dev = driver.findElement(By.xpath("//input[@id='destination-0']"));
		act.moveToElement(dev).click().sendKeys(prop.getProperty("Multi-City")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-4']"))))
				.click();
		driver.findElement(By.xpath("//input[@class='open_calendar']")).click();

		// date
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//div[@id='depart-cal-0']/div[@class='vc-month-box-container']/div[2]/div[6]/div[@class='vc-cell vc-selected-cell']"))))
				.click();

		// multi-city
		driver.findElement(By.xpath("//label[@id='multi-city-label-1']")).click();
		WebElement dest = driver.findElement(By.xpath("//input[@id='destination-1']"));
		act.moveToElement(dest).click().sendKeys(prop.getProperty("Arrival-city")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id='ui-id-6']"))))
				.click();
		driver.findElement(By.xpath("//input[@id='departure-1']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//div[@id='depart-cal-1']/child::div[@class='vc-month-box-container'][@data-month='11']/child::div[@class='vc-month-box']/div[4]/child::div[@class='vc-cell '][@data-date='16']"))))
				.click();
		captureScreenshot(driver, newGeneratedName);

		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='search-flight-btn']"))))
				.click();
		return new FlightBookPage();

	}

}
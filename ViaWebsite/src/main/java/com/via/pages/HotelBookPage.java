package com.via.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import com.via.base.ViaBase;

public class HotelBookPage extends ViaBase {
	String name = "SelectHotel";
	String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

	public String validateHotelBookPage() throws InterruptedException {
		Thread.sleep(10000);
		return driver.findElement(By.xpath("//h4[@class='resCount allHotelCount']")).getText();
	}

	public EnterDetailsPage selectHotelRoom() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath(
				"//div[@id='0']/div[@class='priceDiv']/div[@class='bookSec']/div[@class='selectBtn js-viewRoom via-processed']"))
				.click();
		Thread.sleep(6000);
		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath(
				"//div[@id='0']/div[@id='roomHotel0']/div[@class='eachRoom']/div/div[@class='bookDiv']/div[@class='bookBtn js-bookRoom via-processed']"))
				.click();

		return new EnterDetailsPage();
	}

}

package com.via.pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.base.ViaBase;

public class HotelPage extends ViaBase {

	public HotelBookPage hotelSearch() throws InterruptedException {
		String name = "HotelsearchPage";
		String newGeneratedName = name + "-" + RandomStringUtils.randomNumeric(3);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.xpath("//span[@class='icon via_products_hotel']/following-sibling::span")).click();
		WebElement e = driver
				.findElement(By.xpath("//form[@class='hotelSearchForm']/div[@class='panel u_floatL']/div/div/input"));
		Actions act = new Actions(driver);

		// destination city
		act.moveToElement(e).click().sendKeys(prop.getProperty("Destination")).perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//ul[@id= 'ui-id-1']"))))
				.click();
		act.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();
		captureScreenshot(driver, newGeneratedName);
		driver.findElement(By.xpath("//div[@class='search-hotel u_vertAlignMiddle u_floatR']")).click();
		Thread.sleep(10000);
		return new HotelBookPage();
	}

}

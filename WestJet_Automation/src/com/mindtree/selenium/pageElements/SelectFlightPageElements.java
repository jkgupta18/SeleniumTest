package com.mindtree.selenium.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectFlightPageElements {

	private static WebElement element = null;
	private WebDriver driver;

	public SelectFlightPageElements(WebDriver driver) {

		this.driver = driver;

	}

	public static WebElement select_flight(WebDriver driver) {
		

		element = driver.findElement(By.xpath(".//*[@id='outboundSearchResultsContent']/div/div[7]/div[1]/span[1]"));

		return element;

	}
	
	public static By wait_pageLoad() {

		By element= By.xpath(".//*[@id='outboundSearchResultsContent']/div/div[7]/div[1]/span[1]");

		return element;

	}
}

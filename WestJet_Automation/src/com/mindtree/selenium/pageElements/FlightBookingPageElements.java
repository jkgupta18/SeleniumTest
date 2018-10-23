package com.mindtree.selenium.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightBookingPageElements {

	private static WebElement element = null;
	private WebDriver driver;

	public FlightBookingPageElements(WebDriver driver) {

		this.driver = driver;

	}

	public static WebElement City_From(WebDriver driver) {

		return  driver.findElement(By.id("origin-event"));

		

	}

	public static WebElement CitySearch_From(WebDriver driver) {

		return  driver.findElement(By.id("airportpicker-filter-1"));

		

	}

	public static WebElement City_To(WebDriver driver) {

		return  driver.findElement(By.id("destination-event"));

	

	}

	public static WebElement CitySearch_To(WebDriver driver) {

		return  driver.findElement(By.id("airportpicker-filter-2"));


	}

	public static WebElement Select_Adult(WebDriver driver) {

		return  driver.findElement(By.xpath(".//*[@id='adult-stepper']/button[2]"));


	}

	public static WebElement Select_Child(WebDriver driver) {

		return  driver.findElement(By.xpath(".//*[@id='children-stepper']/button[2]"));


	}

	public static WebElement Move_NextMonth(WebDriver driver) {

		return  driver.findElement(By.xpath(".//*[@id='depart_dw_pnl_0']/div/div/div/div[1]/div/div/div[4]/div"));


	}

	public static WebElement Get_Flights(WebDriver driver) {

		return  driver.findElement(By.xpath(".//*[@id='desktop-submit']/div/input"));


	}

}

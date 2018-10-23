package com.mindtree.selenium.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mindtree.selenium.Utility.CommonDriver;
import com.mindtree.selenium.Utility.ExcelDriver;
import com.mindtree.selenium.Utility.SelectDynamicDate;
import com.mindtree.selenium.pageElements.SelectFlightPageElements;
import com.mindtree.selenium.pageElements.FlightBookingPageElements;

public class FlightBookingTest {

	static WebDriver driver;
	private String String;
	static ExtentReports extent;
	static ExtentTest test;
	
	String[][] Exceldata=new String[6][7];
	Properties prop = CommonDriver.ConfigFile();
	ExcelDriver exceldata=new ExcelDriver();
	//Exceldata=exceldata.readData();
	

	@BeforeTest
	public void extentSetUp() throws IOException {
		CommonDriver com = new CommonDriver();
		extent = com.extentReport();		

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@Test(priority = 1)
	public void OpenBrowser() throws Exception {
		test = extent.createTest("Test1", "Status of test case 1");
		CommonDriver obj = new CommonDriver();
		//driver = obj.openBrowser(prop.getProperty("BrowserType"));
		Exceldata=exceldata.readData();
		driver = obj.openBrowser(Exceldata[0][1]);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.get(prop.getProperty("URL"));
		driver.get(Exceldata[1][1]);
		CommonDriver.takescreenshot(prop.getProperty("screenPath1"));
	}

	@Test(priority = 2)
	public void Flight_Search() {

		test = extent.createTest("Test2", "Status of test case 2");

		FlightBookingPageElements.City_From(driver).click();
		FlightBookingPageElements.CitySearch_From(driver).sendKeys(prop.getProperty("CityFrom"));
		FlightBookingPageElements.CitySearch_From(driver).sendKeys(Keys.ENTER);

		FlightBookingPageElements.City_To(driver).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FlightBookingPageElements.CitySearch_To(driver).sendKeys(prop.getProperty("CityTo"));

		FlightBookingPageElements.CitySearch_To(driver).sendKeys(Keys.ENTER);

		FlightBookingPageElements.Select_Adult(driver).click();
		FlightBookingPageElements.Select_Child(driver).click();
		driver.findElement(By.id("depart")).click();
		CommonDriver.takescreenshot(prop.getProperty("screenPath2"));

		SelectDynamicDate.SelectDate(prop.getProperty("BookingDate"), driver);
		CommonDriver.takescreenshot(prop.getProperty("screenPath3"));

		FlightBookingPageElements.Get_Flights(driver).click();

	}

	@Test(priority = 3)
	public void NextPage_Confirmation() {

		test = extent.createTest("Test3", "Status of test case 3");

		By item = SelectFlightPageElements.wait_pageLoad();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(item));

		String ele = SelectFlightPageElements.select_flight(driver).getText();
		System.out.println(ele);
		Assert.assertEquals(ele, "Select departing flight:");
		System.out.println("Successfully navigated to next page");
		CommonDriver.takescreenshot(prop.getProperty("screenPath4"));

	}

	@AfterTest
	public void tearDown() {
		extent.flush();
	}

}

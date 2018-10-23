package com.mindtree.selenium.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.TemporaryFilesystem;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
import com.google.gson.LongSerializationPolicy;

public class CommonDriver {
	static WebDriver oDriver;
	ExtentHtmlReporter htmlReporter;
	private String String;
	static ExtentReports extent;
	static ExtentTest test;
	static TakesScreenshot myScreenshots;
	static File tempFile, DestFile;
	
	
	Properties prop = CommonDriver.ConfigFile();

	public WebDriver openBrowser(String sBrowserType) throws Exception {

		int index = getBrowserTypeIndexed(sBrowserType);
		switch (index) {
		case 1:
			System.setProperty("webdriver.gecko.driver", prop.getProperty("FireFoxDriverPath"));
			oDriver = new FirefoxDriver();

			break;
		case 2:

			System.setProperty("webdriver.ie.driver", prop.getProperty("IeDriverPath"));
			oDriver = new InternetExplorerDriver();
			break;

		case 3:
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverPath"));
			oDriver = new ChromeDriver();

			break;

		default:
			throw new Exception("Unknow Browser Type = " + sBrowserType);

		}
		return oDriver;
	}
	// -------------------------------------------

	private int getBrowserTypeIndexed(String sBrowserType) {
		sBrowserType = sBrowserType.toLowerCase().trim();

		if (sBrowserType.isEmpty()) {
			return -1;
		}

		if (sBrowserType.equals("ff") || sBrowserType.equals("firefox") || sBrowserType.equals("mozilla")
				|| sBrowserType.equals("mozilla firefox")) {
			return 1;
		}

		if (sBrowserType.equals("ie") || sBrowserType.equals("explorer") || sBrowserType.equals("internet explorer")) {
			return 2;
		}

		if (sBrowserType.equals("chrome") || sBrowserType.equals("google") || sBrowserType.equals("google chrome")) {
			return 3;
		}

		return -1;
	}
	//-----------------------------------------------
	
	public static Properties ConfigFile()   {
		File file = new File(
				"D:\\Selenium\\SeleniumWorkspace\\WestJet_Automation\\src\\src\\conf\\InputData.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("URL"));
		// driver.get(prop.getProperty("URL"));
		return prop;
	}
	
	//------------------------------------------------------------------------------
	
	public ExtentReports extentReport() throws IOException {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\src\\com\\mindtree\\selenium\\Reports\\ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Mac Sierra");
		extent.setSystemInfo("Host Name", "Jitendra");
		extent.setSystemInfo("Environment", "QA");
		//extent.setSystemInfo("User Name", "Krishna Sakinala");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("AutomationTesting.in Report");
		htmlReporter.config().setReportName("Execution Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		return extent;

	}
	
	//-------------------------------------------------------------
	
	public static void takescreenshot(String imagePath) {
		DestFile= new File(imagePath);
		myScreenshots=(TakesScreenshot)oDriver;
		tempFile= myScreenshots.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tempFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

package com.mindtree.selenium.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mindtree.selenium.pageElements.FlightBookingPageElements;

public class SelectDynamicDate {

	public static void SelectDate(String date, WebDriver driver) {

		String selectdate = date;
		Date d = new Date(selectdate);
		SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
		String date1 = dt.format(d);
		String[] split = date1.split("/");
		String month = split[0] + " " + split[2];
		System.out.println(month);

		// day
		while (true) {
			try {
				driver.findElement(By.xpath(".//*[contains(text(), '" + month + "')]")).isDisplayed();

				String[] splitdate = selectdate.split("/");
				int i = Integer.parseInt(splitdate[0]);
				int in = i - 1;
				String day = Integer.toString(in);
				String path = ".//div[@class='dw-cal-table']/div[@class='dw-cal-row']/div[@data-full='2018-6-29']";
				String FirstPart = ".//div[@class='dw-cal-table']/div[@class='dw-cal-row']/div[@data-full='";
				String endPart = "']";
				String Fullpath = FirstPart + splitdate[2] + "-" + day + "-" + splitdate[1] + endPart;
				System.out.println(Fullpath);
				driver.findElement(By.xpath(Fullpath)).click();
				break;

				// driver.findElement(By.xpath("//*[@id='origin-picker']/div[4]/div/ul[1]/li[2]/span")).click();

				// Select dropdown =new Select(select);
				// dropdown.selectByVisibleText("Acapulco");destination-event

			} catch (Exception e) {

				FlightBookingPageElements.Move_NextMonth(driver).click();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}

}

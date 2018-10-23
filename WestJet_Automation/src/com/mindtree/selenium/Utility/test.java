package com.mindtree.selenium.Utility;

public class test {
	static String[][] Exceldata=new String[6][7];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelDriver exceldata=new ExcelDriver();
		Exceldata=exceldata.readData();
		System.out.println("Excel data= "+Exceldata[1][1]);

	}

}

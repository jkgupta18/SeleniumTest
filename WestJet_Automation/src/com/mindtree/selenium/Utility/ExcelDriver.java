package com.mindtree.selenium.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ExcelDriver {
	
	String File_location="D:\\Selenium\\TestExcel.xlsx";
	InputStream myStream;
	Workbook myWorkbook;
	Sheet mySheet;
	int rowCount;
	Row myRow;
	Cell myCell;
	String[][] data=new String[6][7];
	
	
	@Test
	public String[][] readData() {
		try {
			myStream=new FileInputStream(File_location);
			myWorkbook=WorkbookFactory.create(myStream);
			mySheet=myWorkbook.getSheet("data");
			rowCount=mySheet.getPhysicalNumberOfRows();
			System.out.println(rowCount);
			
			for(int i=0;i<rowCount;i++) {
				myRow=mySheet.getRow(i);
				
				for(int j=0; j<myRow.getLastCellNum();j++) {
					myCell=myRow.getCell(j);
					CellType type=myCell.getCellTypeEnum();
					if(type==CellType.NUMERIC) {
						
						data[i][j]=String.valueOf(myCell.getNumericCellValue());
					}else
					{
						data[i][j]=myCell.getStringCellValue();
					}
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}
	
	
	}



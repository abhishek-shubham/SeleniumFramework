package com.qa.abc.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;

public class ReadExcel {
	
	public String readExcelData(String path,String sheetName,int raw, int col) {
		String data = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			Workbook workbook;
			workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			data=sheet.getRow(raw).getCell(col).toString();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	private static final String Test_Data_Sheet_Path = ".\\src\\main\\resources\\testData\\TestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream fis = new FileInputStream(Test_Data_Sheet_Path);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					//data[i][j] = sheet.getRow(i).getCell(j).toString();
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();  //-> if 1st row contains header text
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return data;
	}
}

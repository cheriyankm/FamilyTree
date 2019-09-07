package com.cherry.saya.ft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class SheetCreater {
	public String createNewSheet() throws IOException {
		// Creating Workbook instances
		Workbook wb = new HSSFWorkbook();

		// An output stream accepts output bytes and sends them to sink.
		OutputStream fileOut = new FileOutputStream("Geeks.xls");

		// Creating Sheets using sheet object
		Sheet sheet1 = wb.createSheet("Array");
		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet1.getLastRowNum());
		System.out.println("***********************************************");

		Row row1 = sheet1.createRow(0);
		Cell cell1 = row1.createCell(0);
		for (int j = 1; j < 21; j++) {
			for (int i = 1; i < 21; i++) {
				cell1 = row1.createCell(i - 1);
				cell1.setCellValue(i + j);
			}
			row1 = sheet1.createRow(j);
		}

		Sheet sheet2 = wb.createSheet("String");
		Sheet sheet3 = wb.createSheet("LinkedList");
		Sheet sheet4 = wb.createSheet("Tree");
		Sheet sheet5 = wb.createSheet("Dynamic Programing");
		Sheet sheet6 = wb.createSheet("Puzzles");

		System.out.println("Sheets Has been Created successfully");

		wb.write(fileOut);
		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet1.getLastRowNum());
		System.out.println("***********************************************");

		return "Success";
	}

	public void updateExcelSheet(int k) throws IOException {
		FileInputStream file = new FileInputStream(new File("Geeks.xls"));

		// Create Workbook instance holding reference to .xlsx file
		Workbook wbread = new HSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		Sheet sheet = wbread.getSheetAt(0);

		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet.getLastRowNum());
		System.out.println("***********************************************");

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			System.out.println(row.getRowNum()+" - ");
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// Check the cell type and format accordingly
				switch (cell.getCellType()) {
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() +  "||");
					break;
				case STRING:
					System.out.print(cell.getStringCellValue() + "||");
					break;
				default:
					break;
				}
			}
			System.out.println("");
		}

		HSSFRow sheetrow = (HSSFRow) sheet.getRow(sheet.getLastRowNum()+1);
		if (sheetrow == null) {
			sheetrow = (HSSFRow) sheet.createRow(sheet.getLastRowNum()+1);
		}
		// Update the value of cell
		Cell nthc = sheetrow.getCell(0);
		if (nthc == null) {
			nthc = sheetrow.createCell(0);
		}
		nthc.setCellValue(k);

		FileOutputStream outputStream = new FileOutputStream("Geeks.xls");
		wbread.write(outputStream);

		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet.getLastRowNum());
		System.out.println("***********************************************");
		wbread.close();
		file.close();
		outputStream.close();

		FileInputStream file1 = new FileInputStream(new File("Geeks.xls"));
		Workbook wbread1 = new HSSFWorkbook(file1);

		// Get first/desired sheet from the workbook
		Sheet sheet1 = wbread1.getSheetAt(0);

		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet1.getLastRowNum());
		System.out.println("***********************************************");
		wbread1.close();
	}
	
	public void deleteSheetRow(int rowNumber) throws IOException {
		FileInputStream file1 = new FileInputStream(new File("Geeks.xls"));
		Workbook wbread = new HSSFWorkbook(file1);

		// Get first/desired sheet from the workbook
		Sheet sheet1 = wbread.getSheetAt(0);
		
		Row row = sheet1.getRow(rowNumber);
		sheet1.removeRow(row);
		sheet1.shiftRows(rowNumber+1, sheet1.getLastRowNum(), -1);
		
		FileOutputStream outputStream = new FileOutputStream("Geeks.xls");
		wbread.write(outputStream);

		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet1.getLastRowNum());
		System.out.println("***********************************************");
		wbread.close();
		file1.close();
		outputStream.close();
	}
	
	public void readSheet() throws IOException {

		FileInputStream file = new FileInputStream(new File("Geeks.xls"));

		// Create Workbook instance holding reference to .xlsx file
		Workbook wbread = new HSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		Sheet sheet = wbread.getSheetAt(0);

		System.out.println("***********************************************");
		System.out.println("last row number s1 :" + sheet.getLastRowNum());
		System.out.println("***********************************************");

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			System.out.println(row.getRowNum()+" - ");
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// Check the cell type and format accordingly
				switch (cell.getCellType()) {
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() +  "||");
					break;
				case STRING:
					System.out.print(cell.getStringCellValue() + "||");
					break;
				default:
					break;
				}
			}
			System.out.println("");
		}
	}
}

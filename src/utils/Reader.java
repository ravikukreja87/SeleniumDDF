package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {

	public void readXLSXFile(String fileName) {
		InputStream XlsxFileToRead = null;

		//1. Workbook
		XSSFWorkbook workbook = null;
		try {
			XlsxFileToRead = new FileInputStream(fileName);

			// Getting the workbook instance for xlsx file
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//2. Sheet
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		//3. Row
		XSSFRow row;
		
		//4. Cell/Column
		XSSFCell cell;

		//5. Iterator
		Iterator<?> rows = sheet.rowIterator();

		
		//6. Nested Looping the iterator - Row
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();

			Iterator<?> cells = row.cellIterator();
			
			//7. Loop for column
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();

				if (cell.getCellTypeEnum() == CellType.STRING) {
					System.out.print(cell.getStringCellValue() + " ");
				}
			}
			System.out.println();
			try {
				XlsxFileToRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Reader readXlsx = new Reader();
		readXlsx.readXLSXFile("testdata/credentials.xlsx");
	}
}
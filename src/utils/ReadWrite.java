package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWrite {

	public List<String> username;
	public List<String> password;

	public void writeExcel(String[] result) {
		try {
			File src = new File("testdata/credentials.xlsx");
			src.mkdirs();
			src.createNewFile();
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);
			sh1.getRow(1).createCell(2).setCellValue(result[0]);
			System.out.println("Updated Result");
			sh1.getRow(2).createCell(2).setCellValue(result[1]);
			System.out.println("Updated Result");
			FileOutputStream fout = new FileOutputStream(new File("testdata/credentials.xlsx"));
			wb.write(fout);
			fout.close();
			wb.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void readExcel() {

		try {
			File src = new File("testdata/credentials.xlsx");
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);
			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
			System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
			System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());
			System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
			System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());
			System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());

			username = new ArrayList<String>();
			username.add(sh1.getRow(1).getCell(0).getStringCellValue());
			username.add(sh1.getRow(2).getCell(0).getStringCellValue());

			password = new ArrayList<String>();
			password.add(sh1.getRow(1).getCell(1).getStringCellValue());
			password.add(sh1.getRow(2).getCell(1).getStringCellValue());

			// sh1.getRow(0).createCell(2).setCellValue("2.41.0");
//			sh1.getRow(1).createCell(2).setCellValue("This would be written");
//			sh1.getRow(2).createCell(2).setCellValue("2.39");
//			FileOutputStream fout = new FileOutputStream(new File("testdata/credentials.xlsx"));
//			wb.write(fout);
//			fout.close();
//			wb.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
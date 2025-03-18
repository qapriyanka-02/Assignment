package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
public static FileInputStream fis;
public static XSSFWorkbook workbook;
public static XSSFSheet sheet;	
;
	public static String getcelldata(String filepath, String sheetname, int rownum, int colnum) {
		String cellvalue=null;
		try {
			FileInputStream fis= new FileInputStream("C:\\VoiceGamesAutomation\\com.mypractise\\src\\test\\resources\\Testdata\\testdata_login.xlsx");
			XSSFWorkbook workbook= new XSSFWorkbook(fis);
			XSSFSheet sheet= workbook.getSheet("creds");
			
			if (sheet == null) {
			    throw new RuntimeException("Sheet not found in the workbook!");
			}
			Row row= sheet.getRow(rownum);
			if(row!=null) {
				Cell cell= row.getCell(colnum);
				if(cell!= null) {
					cellvalue= cell.toString();
				}
			}
			
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		return cellvalue;
	}
	

	public static String checkoutdetails(String filepath, String sheetname, int rownum, int colnum) throws IOException {
		fis= new FileInputStream("C:\\VoiceGamesAutomation\\com.mypractise\\src\\test\\resources\\Testdata\\testdata_login.xlsx");
		workbook= new XSSFWorkbook(fis);
	    sheet= workbook.getSheetAt(1);
		
		if (sheet == null) {
		    throw new RuntimeException("Sheet not found in the workbook!");
		}
		return sheetname;
	
}
}

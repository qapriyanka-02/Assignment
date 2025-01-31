package TestClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;


public class Excelutility extends java.com.voicegames.automation.Utility.Common {

	public static String testcase;
	public static XSSFWorkbook workbook;
	public static Cell cell;
	public static Row row;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Sheet sheet;
	public static Cell result_cell;

	
	  public static Map<String,String> getdatafromexcel(){ Map<String,String>
	  testdata= new HashMap<String,String>(); 
	  try 
	  { FileInputStream
	  fileInputStream= new FileInputStream(java.com.voicegames.automation.Utility.Constants.testdatafilepath); 
	  Workbook  workbook= new XSSFWorkbook(fileInputStream); 
	  Sheet sheet  =workbook.getSheetAt(0);
	  int rowcount=sheet.getLastRowNum(); 
	  int colcount=sheet.getRow(1).getLastCellNum(); 
	  System.out.println("row count: " + rowcount + "Column count: " + colcount );
	  for(int i=1; i<=rowcount;i++) 
	  { 
		  Row row=sheet.getRow(i);
		  Cell cell=row.getCell(1); 
		  String promt=row.getCell(1).getStringCellValue();
	  
	  java.com.voicegames.automation.Utility.Common.send_data(promt);
	  
	  Cell keycell=row.getCell(0); String key=keycell.getStringCellValue().trim();
	  Cell valuecell=row.getCell(1); String value= valuecell.getStringCellValue().trim(); 
	  testdata.put(key, value);
	  
	  } } catch (IOException e) {
	  
	  e.printStackTrace(); } return testdata; }
	 

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> getTestDataInMap() {
		List<Map<String, String>> testDataAllRows = null;
		Map<String, String> testdata;

		try {
			FileInputStream fileInputStream = new FileInputStream(java.com.voicegames.automation.Utility.Constants.testdatafilepath);

			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNumber = sheet.getLastRowNum();

			int lastcolNumber = sheet.getRow(0).getLastCellNum();
			List list = new ArrayList();
			for (int i = 0; i < lastcolNumber; i++) { // it will capture all the Column data
				Row row = sheet.getRow(0);
				Cell cell = row.getCell(i);
				String rowHeader = cell.getStringCellValue().trim();
				list.add(rowHeader);
			}
			testDataAllRows = new ArrayList<Map<String, String>>();
			for (int j = 1; j <= lastRowNumber; j++) {
				Row row = sheet.getRow(j);
				testdata = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

				for (int k = 0; k < lastcolNumber; k++) {
					Cell cell = row.getCell(k);
					String colValue = cell.getStringCellValue().trim();
					System.out.println("printing:" + cell.getStringCellValue());
					testdata.put((String) list.get(k), colValue);
				}
				testDataAllRows.add(testdata);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return testDataAllRows;
	}

	public static void setCellData(String sheetname, int rownum, int column, String data) throws IOException {

		File testdatafile = new File(java.com.voicegames.automation.Utility.Constants.testdatafilepath);
		WebElement e= driver.findElement(active_response_xpath);
		if (!testdatafile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(testdatafile);
			workbook.write(fo);
		}
		fi = new FileInputStream(testdatafile);
		workbook = new XSSFWorkbook(fi);

		if (workbook.getSheetIndex(sheetname) == -1) // if sheet does not exist it will create the sheet
			workbook.createSheet(sheetname);
		sheet = workbook.getSheet(sheetname);

		if (sheet.getRow(rownum) == null) // if row not exist create row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(testdatafile);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public static void ComparePartialCellValues() throws FileNotFoundException, IOException {

		String filePath = "..\\Voicegames.com\\src\\main\\resources\\File\\TT_Testdata.xlsx";
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet
			// Get the first cell
			Cell cell1 = sheet.getRow(0).getCell(2);

			// Get the second cell
			Cell cell2 = sheet.getRow(0).getCell(3);

			String str1 = cell1.getStringCellValue();
			String str2 = cell2.getStringCellValue();

			// Set the values of the cells
			cell1.setCellValue(str1);
			cell2.setCellValue(str2);

			// Create a new list to store the partial strings
			List<String> partialStrings = new ArrayList<>();

			// Add the partial strings to the list
			partialStrings.add(str1);
			partialStrings.add(str2);
			// Compare the partial strings of the two cells
			for (String partialString : partialStrings) {
				/*
				 * if (cell1.getStringCellValue().contains(str2)) {
				 * //System.out.println("Cell 1 contains the partial string: " + partialString);
				 * 
				 * }
				 */
				if (cell2.getStringCellValue().contains(str1)) {
					System.out.println("Cell 2 contains the partial string: " + partialString);
					testcase = "PASS";
					Cell result_cell = sheet.getRow(0).getCell(4);
					result_cell.setCellValue(testcase);
				} else {
					// Cell result_cell = sheet.getRow(0).getCell(4);
					testcase = "FAIL";
					result_cell.setCellValue(testcase);
				}
			}
			// Close the workbook
			workbook.close();
		}
	}

	public static void validate_testdata() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(testdatafilepath); 
		Workbook workbook = new XSSFWorkbook(fis);
		
		//fo = new FileOutputStream(testdatafile);
		//workbook.write(fo);
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(1);
		cell = row.getCell(2);
		String expected_response = cell.getStringCellValue();
		System.out.println("Expected Response" + expected_response);

		List<WebElement> response = driver.findElements(represent_xpath);
		System.out.println(response.size());
		for (WebElement webElement : response) {
			String actual_response = webElement.getText();

			// cell=row.getCell(3);
			// String actual_response=cell.getStringCellValue();
			System.out.println("Actual response" + actual_response);
			result_cell = sheet.getRow(1).getCell(4);
			if (expected_response.equalsIgnoreCase(actual_response)) {

				result_cell.setCellValue("PASS");
				fo = new FileOutputStream(testdatafilepath);
				workbook.write(fo);
				workbook.close();
				fi.close();
				fo.close();
			} else
				result_cell.setCellValue("FAIL");
		}
	}
}

package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_SauceDemo {
	
	public  static  WebDriver driver;
	
	/*
	 * public Login_SauceDemo(WebDriver driver) { this.driver=driver; }
	 */
	@BeforeMethod
	@Parameters("browser")
	public void login(String nameofbrowser) throws IOException, InterruptedException {
		
		if(nameofbrowser.equals("chrome")) {
			driver= new ChromeDriver();
		}
		if(nameofbrowser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		if(nameofbrowser.equals("Edge")) {
			driver=new EdgeDriver();
		}
	//	WebDriverManager.chromedriver().setup();
	//	driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		
		FileInputStream fis= new FileInputStream("C:\\VoiceGamesAutomation\\com.mypractise\\src\\test\\resources\\Testdata\\testdata_login.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("creds");
		
		if (sheet == null) {
		    throw new RuntimeException("Sheet not found in the workbook!");
		}
	
	WebElement userfield=driver.findElement(By.id("user-name"));
	WebElement passwordfield=driver.findElement(By.id("password"));
	WebElement login_btn=driver.findElement(By.id("login-button"));
	
	String bckcolor=login_btn.getCssValue("background-color");
	System.out.println("background color is: " + bckcolor);
	

	String username = sheet.getRow(1).getCell(0).getStringCellValue();
	String password=sheet.getRow(1).getCell(1).getStringCellValue();
	
	workbook.close();
	fis.close();
	userfield.clear();
	userfield.sendKeys(username);
	passwordfield.clear();
	passwordfield.sendKeys(password);
	login_btn.click();
	Thread.sleep(2000);
	
	}
	
	@AfterMethod
	public void closebrowser() {
		if(driver!=null) {
			driver.quit();	
		}
		
		
	}
}

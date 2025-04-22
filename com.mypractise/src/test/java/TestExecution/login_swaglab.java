package TestExecution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class login_swaglab {
	
	private WebDriver driver;
	@Test
	@Parameters("browser")
	public void login(String nameofbrowser) {
		
		if(nameofbrowser.equals("chrome")) {
			driver= new ChromeDriver();
		}
		if(nameofbrowser.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		if(nameofbrowser.equals("Edge")) {
			driver= new EdgeDriver();
		}
		WebDriverManager.chromedriver().setup();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}
	
	public void login_creds() {
		WebElement userfield=driver.findElement(By.id("user-name"));
		WebElement passwordfield=driver.findElement(By.id("password"));
		WebElement login_btn=driver.findElement(By.id("login-button"));
	}

}

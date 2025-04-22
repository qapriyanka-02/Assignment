package TestExecution;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class infoprofile_login {
	
	private WebDriver driver;
	@Test
	
	public void login() throws InterruptedException {
	
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://app.infoprofile.com");
		driver.manage().window().maximize();
		//WebElement userfield=driver.findElement(By.xpath("//flt-text-editing-host/input"));
		
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.cssSelector("flutter-view[flt-view-id='0']"));
		element.click();
		
		System.out.println("clicked");
		Thread.sleep(500);
		
		WebElement usertxt=driver.findElement(By.xpath("//body//flutter-view//flt-text-editing-host/input"));
		usertxt.sendKeys("abc@gmail.com");
		System.out.println("text enterd ");	
		driver.close();
	}
}

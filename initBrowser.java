package java.com.voicegames.automation.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ResponseCodec;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class initBrowser {

	
	public static WebDriver driver;
	@BeforeMethod
	public static void launchbrowser() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://shop.advanceautoparts.com");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
	}
	@AfterTest
	public static void closeBrowser() {
		driver.quit();	
	}
	@Test(enabled=false)
	public static void checkURLS() throws IOException {
		List <WebElement> allurls = driver.findElements(By.tagName("a")); 
		System.out.println("List of all the webelements: " + allurls.size());
		
		for(WebElement link: allurls) { 
			String url= link.getAttribute("href");
		
		if(url==null || url.isEmpty() || url.startsWith("javascript")) {
			System.out.println("Skip url liink" + url);
			continue;
		}
		checkbrokenlink(url);
		}
		
		
	}

	public static void checkbrokenlink(String linkurl) throws IOException {
		
		URL url= new URL(linkurl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		
		int reponseCode= connection.getResponseCode();
		
		if(reponseCode>=400) {
			System.out.println("Broken URL " + linkurl + "Status" + reponseCode);
		}
		else
		{
			System.out.println("Valid URL" + linkurl + "status" + reponseCode);
		}
		
	}
	
	@Test(enabled=false)
	public static void checkBackColor() {
		
		WebElement button= driver.findElement(By.xpath("//div[text()='Add a vehicle']"));
		String Actualbgacolor= button.getCssValue("color");
		System.out.println("Background color" + Actualbgacolor);
		Color color= Color.fromString(Actualbgacolor);
		String 	ActualHexcolor = color.asHex();
		System.out.println("Hexcolor: " + ActualHexcolor);
		
	}
	
	@Test(enabled=false)
	public static void checkTitle() {
		
		System.out.println("category names " + driver.findElement(By.xpath("//ul[@class='css-jungqb']//li[1]")).getText());
		System.out.println("category names " + driver.findElement(By.xpath("//ul[@class='css-jungqb']//li[2]")).getText());
		System.out.println("category names " + driver.findElement(By.xpath("//ul[@class='css-jungqb']//li[3]")).getText());
		System.out.println("category names " + driver.findElement(By.xpath("//ul[@class='css-jungqb']//li[4]")).getText());
		System.out.println("category names " + driver.findElement(By.xpath("//ul[@class='css-jungqb']//li[5]")).getText());
		
	}
	
	@Test
	public static void scrollAccount() throws InterruptedException {
		
		WebElement ele= driver.findElement(By.xpath("//*[text()='Account']"));
		ele.click();
		WebElement createAccount=driver.findElement(By.xpath("//*[text()='Create an Account']"));
		Actions action = new Actions(driver);
		action.moveToElement(createAccount);
		Thread.sleep(3000);
		action.perform();
		createAccount.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='phone']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("//div[text()='Enter a 10-digit Phone Number']")).getText());
		
		
	}
	

}

package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;



public class Generic_Flow extends java.com.voicegames.automation.Utility.Common {

	public static void start_flow() throws InterruptedException {
		
		
		Thread.sleep(1000);
		driver.findElement(skill_search).click();
		driver.findElement(skill_search).sendKeys("today's trivia");
		Thread.sleep(1000);
		driver.findElement(skill_name).click();
		scroll_down();
		Thread.sleep(1000);
		wait_for_element(monitize_next_btn);
		driver.findElement(monitize_next_btn).click();
		Thread.sleep(1000);
		scroll_down_purchase_reset();
		Thread.sleep(1000);
		driver.findElement(reset_popup).click();
		driver.findElement(bnttest).click();
		Thread.sleep(500);
		//driver.findElement(uttrance_ok).click(); // not everytime it display the uttrance pop up
	}
	
	public static void scroll_down() throws InterruptedException {
		WebElement we = driver.findElement(monitize);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", we);
		we.click();
		Thread.sleep(300);
	}
	
	public static void scroll_down_purchase_reset() throws InterruptedException {
		WebElement we = driver.findElement(reset_purchase);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", we);
		we.click();
		Thread.sleep(300);
	}
	
	
	public static void pop_up_close_reset_popup() {
	driver.findElement(By.xpath("//span[text()='Close']")).click();	 //next pop up 
	}

	
}

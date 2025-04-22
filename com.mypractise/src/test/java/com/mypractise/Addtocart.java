package com.mypractise;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.ExcelReader;
import Utility.Login_SauceDemo;
import java.net.URL;

public class Addtocart extends Login_SauceDemo {


	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;	
	public  double total_price=0.0;
	
	public void proceedtocart() throws InterruptedException, IOException {
		
		
		List<WebElement> btn_cart=driver.findElements(By.xpath("//div[@class='pricebar']/button[text()='Add to cart']"));
																//div[@class='pricebar']/*[text()='Add to cart']
		JavascriptExecutor js= (JavascriptExecutor)driver;
		scroll_down();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		for(WebElement button: btn_cart) {
			button.click();
		}	
		System.out.println("items added to cart");
		scroll_up();
		System.out.println("scrolled up");
		Thread.sleep(2000);
		
		WebElement cart= driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));
		cart.click();
		cart_items_price();
		check_out_item_price();
		finish();	
		valid_all_URLs();
	}
	public void valid_all_URLs() throws MalformedURLException, IOException {
		
		List<WebElement> allinks= driver.findElements(By.tagName("a"));
		System.out.println("links of webpage: "+ allinks.size() );	
				
				for(WebElement links: allinks) {
			    String url=links.getAttribute("href");
			if(url!=null & !url.isEmpty()) {
				check_URLS(url);
			}else
			{
				System.out.println("empty or null url");
			}
		}
	}
	
	public void check_URLS(String url) throws MalformedURLException, IOException {
		try {
		HttpURLConnection connection= (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		
		int responsecode=connection.getResponseCode();
		
		if(responsecode>=200 && responsecode<300) {
			System.out.println(url + "valid urls" + responsecode);
		}
		else
		{
			System.out.println(url + "broken" + responsecode);
		}	
		}	catch (Exception e) {
			System.out.println("invalid url");
		}
	}
	public void finish() {
		
		WebElement btn_finish= driver.findElement(By.cssSelector("#finish"));
		btn_finish.click();
		WebElement txt_orderconfirm=driver.findElement(By.cssSelector(".complete-text"));
		System.out.println("Order confirmation text: " + txt_orderconfirm.getText());
		String actual= txt_orderconfirm.getText();
		String str="Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		assertEquals(actual, str,"title does not matched");
	}
		public void cart_items_price() {
			WebElement cartcountelement=driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a/span"));
			String cart_item_count=cartcountelement.getText();
			int cartcount=Integer.parseInt(cart_item_count);
			System.out.println("count of item in cart" + cartcount);		
			int actual_count=6;
			Assert.assertEquals(actual_count, cartcount);
			
			List<WebElement> item_price= driver.findElements(By.xpath("//div[@data-test='inventory-item-price']"));
		    System.out.println("Quantity of items" + item_price.size());
		    
		   total_price=0.0;
		    double price_count = 0;
		   // double cart_item=0;
		   String price = null;
			for(WebElement product_price: item_price) {
				 price= product_price.getText().replace("$", "").trim();				
				 System.out.println("price" + price);	
				 price_count=Double.parseDouble(price);	
				 total_price +=price_count;
				 System.out.println("cart calculation: " + total_price);
			}
			
		}
		public void check_out_item_price() throws IOException {
			
			WebElement btn_checkout= driver.findElement(By.xpath("//*[@id='checkout']"));
			btn_checkout.click();
			checkout();
			WebElement btncontinue=driver.findElement(By.xpath("//*[@id='continue']"));
			btncontinue.click();
			
			WebElement final_cart=driver.findElement(By.cssSelector(".summary_subtotal_label"));
			String final_cart_count= final_cart.getText();
			String number_only;
			number_only=final_cart_count.replaceAll("[^0-9.]", "").trim();
			
			
			double cart_cal=0.0;
			final_cart_count=final_cart.getText().replace("$", "").trim();	
			System.out.println("final count of cart items" + number_only);	
			cart_cal=Double.parseDouble(number_only);
			System.out.println("print final count: " + cart_cal);
			if(total_price == cart_cal) {
				System.err.println("cart value matched");
			}
			else
			{
				System.out.println("cart value did not matched");
			}
			
		}
	public void scroll_down() {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	
	public void scroll_up() {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public void checkout() throws IOException {
		
		fis= new FileInputStream("C:\\VoiceGamesAutomation\\com.mypractise\\src\\test\\resources\\Testdata\\testdata_login.xlsx");
		workbook= new XSSFWorkbook(fis);
	    sheet= workbook.getSheetAt(1);
	    
		if (sheet == null) {
		    throw new RuntimeException("Sheet not found in the workbook!");
		}
		
		WebElement name_field=driver.findElement(By.xpath("//*[@id='first-name']"));
		WebElement last_name_field=driver.findElement(By.xpath("//*[@id='last-name']"));
		WebElement postal_code_field=driver.findElement(By.xpath("//*[@id='postal-code']"));
				
		String name=sheet.getRow(1).getCell(0).getStringCellValue();
		String last_name=sheet.getRow(1).getCell(1).getStringCellValue();
		String postal_code=sheet.getRow(1).getCell(2).getStringCellValue();		
		workbook.close();
		fis.close();	
		
		name_field.sendKeys(name);
		last_name_field.sendKeys(last_name);
		postal_code_field.sendKeys(postal_code);
		System.out.println("Capture the checkout info from excel");
		
	}
	
	public void getwindowhandles() {
		
		String main_window=driver.getWindowHandle();
		
		System.out.println("main window" + main_window);
		
		Set<String> allwindow=driver.getWindowHandles();
		
		for(String window:allwindow) {
			
			if(!window.equals(main_window)) {
				driver.switchTo().window(window);
				System.out.println("switch to new window"+ driver.getTitle());
			break;
			}
			
			driver.switchTo().window(main_window);
		}
		
		
	}
	
}

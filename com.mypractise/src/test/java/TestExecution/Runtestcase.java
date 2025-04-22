package TestExecution;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.mypractise.Addtocart;

import Utility.Login_SauceDemo;

public class Runtestcase extends Login_SauceDemo{

	

	@Test(groups="sanity", enabled = false)
	public void execute_testcase1() throws InterruptedException, IOException {
		
		Addtocart obj= new Addtocart();
		obj.proceedtocart();
	}
	
	@Test(groups="smoke",enabled=true)
	public void execute_testcase2() throws InterruptedException, IOException {
		
		Addtocart obj= new Addtocart();
		obj.proceedtocart();
	}
}

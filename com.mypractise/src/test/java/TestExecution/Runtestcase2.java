package TestExecution;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.mypractise.Addtocart;

import Utility.Login_SauceDemo;

public class Runtestcase2 extends Login_SauceDemo{

	

	@Test
	public void execute_testcase1() throws InterruptedException, IOException {
		
		Addtocart obj= new Addtocart();
		obj.proceedtocart();
	}
}

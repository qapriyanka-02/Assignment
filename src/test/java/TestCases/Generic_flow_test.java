package TestCases;

import java.com.voicegames.automation.Utility.Common;

import org.testng.annotations.Test;

import TestClasses.Generic_Flow;




public class Generic_flow_test {
	
	@SuppressWarnings("static-access")
	@Test
	public void RunGenericFlow() throws InterruptedException {
		
		Generic_Flow obj= new Generic_Flow();
		Common.launch_browser();
		obj.start_flow();
	}
	

}

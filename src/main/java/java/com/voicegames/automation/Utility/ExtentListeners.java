package java.com.voicegames.automation.Utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener{
	
	
	public void onTestSuccess(ITestResult result) {
		ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel(result.getName().toUpperCase()+":PASS", ExtentColor.GREEN));	
	}
	
	public void onTestFail(ITestResult result) {
		ExtentReport.test.log(Status.FAIL, result.getThrowable().getMessage());
		ExtentReport.test.log(Status.FAIL,MarkupHelper.createLabel(result.getName().toUpperCase()+":FAIL",ExtentColor.RED));
	}
}

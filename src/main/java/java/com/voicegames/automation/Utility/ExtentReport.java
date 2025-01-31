package java.com.voicegames.automation.Utility;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public static ExtentSparkReporter sparkreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ITestResult result;
	WebDriver driver;
	
	public static  void initializeReport() {
		
		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/VoiceGamesReport.html");
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Voice Games Automation Report");
		sparkreporter.config().setTheme(Theme.STANDARD);
		sparkreporter.config().getTimeStampFormat();
		sparkreporter.config().setTimeStampFormat("EEEE,MMMM dd, yyyy,hh:mm a'('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
	}
	
	public static  String captureScreenshot(WebDriver driver) throws IOException {
		String Fileseparator= System.getProperty("file.separator");
		String extent_report_path="."+Fileseparator+"Reports";
		String Screenshotpath=extent_report_path+Fileseparator+"screenshots";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotName="screenshot"+Math.random()+".png";
		String screenshotPath=Screenshotpath+Fileseparator+screenshotName;		
		FileUtils.copyFile(src,new File(screenshotPath));
		return "."+Fileseparator+"screenshots"+Fileseparator+screenshotName;
	}

	 
}

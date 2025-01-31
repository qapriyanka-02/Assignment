package java.com.voicegames.automation.Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants extends Locators{
	
	public static WebElement ele = null;
	public static final String url="";
	public static String alexaQuestion;
	public static String alexaExpl;
	public static WebDriverWait wait;
	public static int questionCount = 0;
	public final static String questionfile="..\\testing-automation\\src\\main\\resources\\File\\QuestionSet.json";			              
	public final static String csvpath="..\\testing-automation\\src\\main\\resources\\File\\Response_file.csv";
	public final static String testdatafilepath="..\\testing-automation\\src\\main\\resources\\File\\TT_IN_TestData.xlsx";
	public final static String languageResource="..\\testing-automation\\src\\main\\resources\\File\\languageResources";
	public final static String Us_testdata="..\\testing-automation\\src\\main\\resources\\File\\US_Locale_TestData.xlsx";
	public final static String ReturnUserpath="..\\testing-automation\\src\\main\\resources\\File\\Return_User.xlsx";
	public final static String StreaxCountFilePath="..\\testing-automation\\src\\main\\resources\\File\\StreaxCount.txt";
	public final static String Interactionmodalpath="..\\testing-automation\\src\\main\\resources\\File\\newjson.json";

	
	public static String state="Maharashtra";
	public static String confirm_yes="yes";
	public static String confirm_no="No";
	public static String input="Alexa open today's trivia";
	public static String search_game="today's trivia";
	public static String username="voicegames94@gmail.com";
	public static String password="voice1st@94";
	public static String stop="STOP";
}

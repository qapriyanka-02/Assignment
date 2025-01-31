package java.com.voicegames.automation.Utility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Replace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.impl.StringArraySerializer;

public class Common extends Constants {

	public static WebDriver driver;
	public static String response;
	public static String expl;
	public static String alexaExpll;
	public static String cal_score;
	public static String cal_streax_str;
	public static WebElement lbFlowEle = null;
	
	@BeforeMethod
	public static void launch_browser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setExperimentalOption("debuggerAddress", "localhost:60753");
		driver = new ChromeDriver(opt);
		driver.navigate().refresh();
		Thread.sleep(1000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public static int calculate_Score() throws IOException {

		int Today_que = 0;
		int Bonus_que = 0;
		int Challenge_que = 0;
		int TotalCount = 0;
		int questionCount = 0;
		while (questionCount < 3) {
//			if(questionCount < 3)
			switch (questionCount) {
			case 0: {
				generic_question_flow(true);
				Today_que = Today_que + 5;
				System.out.println("Todays Question score: " + Today_que);
				break;
			}
			case 1: {
				generic_question_flow(true);
				Bonus_que = Bonus_que + 3;
				System.out.println("Bonus Question score: " + Bonus_que);
			}
			default: {
				wait_for_element(challenge_que);
				for (int i = 1; i <= 3; i++) {
					generic_question_flow(true);
					Challenge_que = Challenge_que + 10;
					System.out.println("Challenge question count: " + Challenge_que);
				}
				questionCount = 5;
			}
			}
			TotalCount = Today_que + Bonus_que + Challenge_que;
			System.out.println("Total score of a user is: " + TotalCount);
			questionCount++;
			System.out.println("printing question count" + questionCount);
		}
		return TotalCount;
	}

	public static void save_expl() throws InterruptedException {
		Thread.sleep(5000);
		By str = short_audio;
		if (str.equals(short_audio)) {
			WebElement explanation = driver.findElement(active_response_xpath);
			Thread.sleep(5000);
			explanation.getText();
			alexaExpll = explanation.getText();
			System.out.println("Ques: " + alexaQuestion);
			System.out.println("Expl: " + alexaExpll);
			GetExplanation();
		}
	}

	public static void GetExplanation() {
		try {
			boolean foundQuestion = false;
			JSONParser jsonparsar = new JSONParser();
			FileReader reader = new FileReader(questionfile);
			System.out.println();
			Object obj = jsonparsar.parse(reader);
			JSONArray all_quest_json = (JSONArray) obj;

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> arrayIterator = all_quest_json.iterator();
			while (arrayIterator.hasNext()) {
				JSONObject tempObject = arrayIterator.next();
				String tempq = (String) tempObject.get("que");
				if (tempq.equals(alexaQuestion)) {
					JSONArray explArr = (JSONArray) tempObject.get("expl");
					expl = explArr.get(0).toString();
					System.out.println("Printing Expalnation from json : " + expl);
					foundQuestion = true;
					if (alexaExpll.contains(expl)) {
						boolean doesContain = alexaExpll.contains(expl);
						assertTrue(doesContain, "Answer explaination from source file");
						System.out.println("Answer explaination after assertion: " + alexaExpll);
						ExtentReport.test.pass("Answer explaination" + alexaExpll);
					}
					Assert.assertTrue(true);
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception caught in GetExplanation function" + e);
		}
	}

	public static void Webelementclick(WebElement e) {
		e.click();
	}

	public static boolean GetAnswer(boolean shouldAnswerCorrect) {
		boolean foundQuestion = false;
		try {
			JSONParser jsonparsar = new JSONParser();
			FileReader reader = new FileReader(questionfile);
			System.out.println();
			Object obj = jsonparsar.parse(reader);
			JSONArray all_quest_json = (JSONArray) obj;
			Iterator<JSONObject> arrayIterator = all_quest_json.iterator();
			while (arrayIterator.hasNext()) {
				JSONObject tempObject = arrayIterator.next();
				String tempq = (String) tempObject.get("que");
				// if (tempq.equals(alexaQuestion)) {
				if (tempq.contains(alexaQuestion)) {
					JSONArray answerArray = (JSONArray) tempObject.get("ans");
					JSONArray optionsArray = (JSONArray) tempObject.get("options");
					foundQuestion = true;
					System.out.println("Print Answer" + answerArray);
					if (shouldAnswerCorrect) {
						String jsonAnswer = (String) answerArray.get(0);
						String ansToSend = InteractionModel.findSynonyms("OPTION_VALUE", jsonAnswer);	
						String myStr = "{OPTION_VALUE}";
						String uttrencetosend=InteractionModel.findUtterances("ANSWER_INTENT", "OPTION_VALUE");
						String finaluttrence = uttrencetosend.replace(myStr,ansToSend);
						System.out.println("Replacing my string " + finaluttrence);
						//ele.sendKeys(ansToSend);	
						ele.sendKeys(finaluttrence);
						
					}
					else {
						optionsArray.remove(optionsArray.indexOf(answerArray.get(0)));
						System.out.println("Removed correct options" + optionsArray.toString());
						ele.sendKeys((String) optionsArray.get(0));
					}
					press_enter();
					ExtentReport.test.log(Status.INFO,"Questions from alexa");
					ExtentReport.test.pass(answerArray.toString() + " ****Answer**** " + alexaQuestion);					
					ExtentReport.test.log(Status.INFO, " ****answer Explaination**** ");
					Assert.assertTrue(true,"Assert condition true after user answered the input");
					Thread.sleep(3000);
					save_expl();
					//continue_alexa();
					break;
				}
			}
			if (!foundQuestion) {
				ExtentReport.test.log(Status.FAIL,"This Question is not found in database");
				System.err.println("ERROR: Question not found in database");
				ele.sendKeys(stop);	
				//ele.sendKeys("Next"); commented coz game is stopped
				press_enter();
			}

		} catch (Exception e) {
			System.out.println("Exception caught" + e);
		}
		return shouldAnswerCorrect && foundQuestion;
	}

	public static void continue_alexa() {
		wait_for_element(short_audio);
		send_data("Continue");
		press_enter();
	}

	public static int getScore(String str) {

		String result = "";
		String pointsRegex = ("(\\d{0,9})\\.");
		Pattern pattern = Pattern.compile(pointsRegex);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			result = matcher.group();
			result = String.valueOf(result.toCharArray(), 0, result.length() - 1);
			if (result != null)
				break;
			System.out.println("Matched : " + matcher.group());
		}
		return Integer.parseInt(result.trim());
	}
	public static int getStatistics(String str) {
        System.out.println("get streax" + str);
		String result = "";
		String pointsRegex = ("Your streak is (\\d+).");
		Pattern pattern = Pattern.compile(pointsRegex);
		Matcher matcher = pattern.matcher(str);
		int retVal = 0;
		while (matcher.find()) {
			result = matcher.group();
			result = String.valueOf(result.toCharArray(), 0, result.length() - 1);
			result = result.replaceAll("Your streak is ", "");
			retVal = Integer.parseInt(result);
			System.out.println("Matched : " + matcher.group() + " RetVal: " + retVal);
			if (result != null)
				break;
		}
		//return Integer.parseInt(result.trim());
		return retVal;
	}
	public static String getquestion(String str) {

		String result = "";
		String regex = ("(?<=\\.)[^.]*?(?=\\?)"); // after dot only
		String regex_fromstart = ("(.*)\\?");
		// Create a Pattern objectPattern
		Pattern pattern = Pattern.compile(regex);
		// Create a Matcher objectMatcher
		Matcher matcher = pattern.matcher(str);
		// Find matching strings
		while (matcher.find()) {
			result = matcher.group() + "?";
		}
		if (result.equals("") || result.equals("?")) {
			pattern = Pattern.compile(regex_fromstart);
			// Create a Matcher objectMatcher
			matcher = pattern.matcher(str);
			while (matcher.find()) {
				result = matcher.group(); // + "?";
			}
		}
		return result.trim();
	}

	public static boolean generic_question_flow(boolean shouldAnswerCorrect) throws IOException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(400));
		wait_for_element(By.xpath(String.format(xpathFormat, "? A:")));
		WebElement quest = driver.findElement(By.xpath(String.format(xpathFormat, "?")));
		response = quest.findElement(active_response_xpath).getText();
		quest.getText();
		alexaQuestion = getquestion(quest.getText());
		System.out.println("Getting Alexa question : " + alexaQuestion);
		return GetAnswer(shouldAnswerCorrect);
	}

	public static void bootstrap_dropdown() throws InterruptedException {
		
		wait_for_element(drp_toggle_xpath);
		driver.findElement(drp_toggle_xpath).click();
		wait_for_element(drp_off);
		driver.findElement(drp_off).click();
		refresh();
		wait_for_element(drp_toggle_xpath);
		driver.findElement(drp_toggle_xpath).click();
		wait_for_element(drp_dev);
		driver.findElement(drp_dev).click();
		Thread.sleep(1000);
	}
	public static void drp_selectLocal() throws InterruptedException {

     	driver.findElement(drp_local).click();	
		WebElement w=driver.findElement(drp_local);
		String innerhtml = w.getAttribute("innerHTML"); // to find the id of the bootstrap dropdown
		System.out.println("inner HTML"+ innerhtml);
		wait_for_element(drp_US);
		driver.findElement(drp_US).click();
		System.out.println("Selected US dropdown value");
	}
	public static void drp_IN_Local() throws InterruptedException {

     	driver.findElement(drp_local).click();	
		wait_for_element(drp_IN);
		driver.findElement(drp_IN).click();
		System.out.println("Selected INDIA Locale");
	}
	public static void send_data(String str) {
		ele.sendKeys(str);
	}
	
	public static void press_enter() {
		ele.sendKeys(Keys.ENTER);
	}

	public static void leaderboard_flow(int calculatedScore)
			throws IOException, InvalidFormatException, InterruptedException {

		wait_for_element(leaderboard_xpath);
		lbFlowEle = driver.findElement(leaderboard_xpath);
		send_data(confirm_yes);
		press_enter();

		wait_for_element(represent_xpath);
		lbFlowEle = driver.findElement(represent_xpath);
		send_data(state);
		press_enter();

		wait_for_element(state_xpath);
		lbFlowEle = driver.findElement(state_xpath);
		send_data(confirm_yes);
		press_enter();

		wait_for_element(statics_xpath);
		lbFlowEle = driver.findElement(statics_xpath);
		send_data(confirm_yes);
		press_enter();

		Thread.sleep(5000);
		WebElement score_ele = driver.findElement(final_score);
		wait_for_element(final_score);
		cal_score = score_ele.getText();
		System.out.println("Print todays question score after assertion: " + getScore(cal_score));
		int webscore = getScore(cal_score);
		assertEquals(calculatedScore, webscore);
		Assert.assertTrue(true);
		ExtentReport.test.log(Status.PASS, "Score match with the score calculated on webconsole");
		
		wait_for_element(do_u_want_try);
		lbFlowEle = driver.findElement(do_u_want_try);
		send_data(confirm_no);
		press_enter();
		
	}

	public static void wait_for_element(By xpath) {
		
		 wait.until(ExpectedConditions.elementToBeClickable(xpath));
		
	}

	public static void refresh() {
		System.out.println("Refresh Executed");
		driver.navigate().refresh();
	}

	public static void GetAnswer() {
		GetAnswer(true);
	}

}

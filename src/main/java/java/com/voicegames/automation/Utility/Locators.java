package java.com.voicegames.automation.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Locators {               
	
	public final static String xpathFormat ="//div//p[@class='askt-dialog__message askt-dialog__message--active-response'][contains(normalize-space(text()),'%s')]";
	public final static By active_response_xpath= By.xpath("//div//p[@class='askt-dialog__message askt-dialog__message--active-response']");
	
	public static By shortaudio=(By.xpath(String.format(xpathFormat,"<Short audio>")));
	public static By input_xpath=By.xpath("//*[@id=\"astro-tabs-1-panel-0\"]/div[1]/div[2]/div[1]/div[1]/input");
	public static By response_xpath = By.xpath("//div//p[@class='askt-dialog__message askt-dialog__message--active-response'][contains(text(),'%s')]");

	public static By all_element_xpath=(By.xpath("//*[@id=\"astro-tabs-1-panel-0\"]/div[2]"));
	public static By short_audio=(By.xpath(String.format(xpathFormat,"<Short audio>")));
	public static By user_input=(By.xpath(String.format(xpathFormat,"<User Touch Event>")));
	
	public static By todayque=(By.xpath(String.format(xpathFormat,"Here is Today's Question.")));
	public static By bonusque=(By.xpath(String.format(xpathFormat,"Bonus Question.")));
	public static By challengeque=(By.xpath(String.format(xpathFormat," challenge question.")));
	
//leaderboard xpath
	public static By leaderboard_xpath=By.xpath(String.format(xpathFormat,"Would you like to join Leaderboard?"));
	public static By represent_xpath= (By.xpath(String.format(xpathFormat, "Which state would you like to represent?")));
	public static By state_xpath= (By.xpath(String.format(xpathFormat, "I heard maharashtra, is that right?")));
	public static By statics_xpath= (By.xpath(String.format(xpathFormat, "statistics?")));
	public static By do_u_want_try= (By.xpath(String.format(xpathFormat, "Do you want to try it?")));
	public static By skill_connection=By.xpath("//div//p[@class='askt-dialog__message askt-dialog__message--active-response'][contains(text(),'Do you want to try it?')]");	
//dropdown xpath
//	public static By streax_xpath= (By.xpath(String.format(xpathFormat, "Your streak is")));
	public static By streax_xpath= (By.xpath("//p[@class='askt-dialog__message askt-dialog__message--response'][contains(text(),'Your streak is')]"));
	public static By drp_toggle_xpath=(By.xpath("//div[@class='askt__toggle--dropdown-above']"));
	public static By drp_outerMenu_xpath=(By.xpath("//div[@class='Select-menu-outer']"));
	public static By drp_off=(By.xpath("//*[@id='react-select-2--option-2' and contains(text(),'Off')]"));
	public static By drp_above=((By.xpath("//div[@class='askt__toggle--dropdown-above']")));
	public static By drp_dev=(By.xpath("//*[@id='react-select-2--option-0' and contains(text(),'Development')]"));
//score
	public final static By score1=(By.xpath(String.format(xpathFormat,"Here is Today's Question")));
	public static By final_score=(By.xpath(String.format(xpathFormat,"Your total points are")));
	public final static By challenge_que=(By.xpath(String.format(xpathFormat,"challenge")));

//US Local
	
	public static By claim_rewards= (By.xpath(String.format(xpathFormat,"Would you like to start your seven-day free trial?")));
	public static By drp_local=(By.xpath("//div[@id='react-select-3--value']"));
	public static By drp_US=(By.xpath("//*[@aria-label='English (US)']"));
	public static By drp_IN=(By.xpath("//*[@aria-label='English (IN)']"));
	public static By deault_payment_xpath= (By.xpath(String.format(xpathFormat,"default payment method?")));
	public static By US_ask__leaderboard=(By.xpath(String.format(xpathFormat,"Would you like to join?")));
	public static By trial_xpath=(By.xpath(String.format(xpathFormat,"rewards start ur free trial")));
	public static By US_state_xpath= (By.xpath(String.format(xpathFormat, "is that right?")));
	public static By connection_xpath=(By.xpath(String.format("May I send the request to")));
	public static By great_news=(By.xpath("//*[@id=\"astro-tabs-1-panel-0\"]/div[2]/div[6]/p"));
	//public static By streax_xpath=(By.xpath("//*[@id=\"astro-tabs-1-panel-0\"]/div[2]/div[31]/p/text()"));
	//Login
	
	 // public static WebElement btnsign=(WebElement)(By.xpath("//*[@id=\"a-page\"]/header/div[1]/div[2]/div[2]/a"));
	 // public static By txtusername=(By.xpath("//input[@name='email']")); 
	//  public static By txtpassword=(By.xpath("//input[@name='password']")); 
	//  public static By btnsubmit= (By.xpath("//input[@type='submit']")); 
//	  public static WebElement btndevloperconsole=(WebElement) (By.xpath("//a[@href='/home.html' and contains(text(),'Developer Console')]")); 
//	  public static  WebElement lnkalexa_skillkit=(WebElement)(By.xpath("//div[@class='dpdtiText']//a[contains(text(),'Alexa Skills Kit')]"));
	  
//	  public static WebElement lnktodaystrivia=(WebElement)(By.xpath("//a[@href='/alexa/console/ask/build/custom/amzn1.ask.skill.93048ce1-d3df-468e-b7ce-26ca88f12a20/development/en_IN/dashboard']"));
	  public static By your_skill=(By.xpath("//a[text()='Your Skills']"));
	  public static By skill_search=(By.xpath("//input[@id='skill-search-input']"));
	  public static By skill_name=(By.xpath("//span[@class='astro-column flex-1  skill-list-page__name p-a-0']//a//span[text()]"));
	  public static By bnttest=(By.xpath("//div//a[@id='test']"));
	  public static By monitize=(By.xpath("//a[text()='Monetize Your Skill']"));
	  public static By monitize_next_btn=(By.xpath("//button[@id='monetization-method-next-btn']"));
	  public static By reset_purchase=(By.xpath("//a[text()='Reset test purchases']"));
	  public static By reset_popup=(By.xpath("//button[text()='Reset']"));
	  public static By uttrance_ok=By.xpath("//button[@title='Close']");
}
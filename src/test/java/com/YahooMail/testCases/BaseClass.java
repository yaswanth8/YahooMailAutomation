package com.YahooMail.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.YahooMail.pageObjects.LoginMailPage;
import com.YahooMail.utilities.ReadConfig;
import com.YahooMail.utilities.XLUtils;



public class BaseClass {


	ReadConfig readconfig=new ReadConfig();

	public String baseURL=readconfig.getAppliactionURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getpassword();
	public String chromeBrowserclearDatapath=readconfig.getChromeclearDatapath();
	public String launchVpn=readconfig.getLaunchVpn();
	public String exitVpn=readconfig.getexitVpn();
	public static String userName;
	public static String cityName;
	public static String browserName;

	public static WebDriver driver;
	public static Logger logger;



	@Parameters("browser")
	@BeforeClass
	public void setup(String br){
		logger=Logger.getLogger("Yahoo Mail");
		PropertyConfigurator.configure("log4j.properties");
		browserName=br;
		
		if(br.equals("chrome")) {
		
			System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
			driver= new ChromeDriver();
			
			}	
		else if(br.equals("firefox")) {
		
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
			driver= new FirefoxDriver();
			
			}
		
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@AfterClass
	public void tearDown(){
		driver.quit();
		logger.info("Completed All mail IDs");
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException, InterruptedException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		
		
	}

	public String randomestring(){
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

	@DataProvider(name="LoginData")
	String [][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"/src/test/java/com/YahooMail/testData/BookFramewrk.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++){
			
			for(int j=0;j<colcount;j++){
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
				}	
			}	
		return logindata;
		}

// Go to default tab
	public void defaulttab() throws InterruptedException{
	  
		Thread.sleep(5000);
		String originalHandle = driver.getWindowHandle();
	   //Do something to open new tabs
	
	    for(String handle : driver.getWindowHandles()) {
	       if (!handle.equals(originalHandle)) {
	           driver.switchTo().window(handle);
	           
	           try{
	           JavascriptExecutor js = (JavascriptExecutor) driver;
	           
	           js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	           Thread.sleep(4000);
	           js.executeScript("window.scrollTo( 0,-document.body.scrollHeight)");
	           Thread.sleep(4000);
	           
	           }
	           catch(Exception e){
	            logger.info("New tab failed");
	           }
	
	           driver.close();
	       }
	    }
	    
   driver.switchTo().window(originalHandle);
	}


	//-------------------CACHE CLEAR-------------------//
	
	public void clearCacheChrome(int noOfTabs,int noOfEnters) throws InterruptedException	{
		
		Thread.sleep(2000);
		driver.get(chromeBrowserclearDatapath);
		Thread.sleep(3000);
		if (driver.getTitle().contains("Settings"))
		{
			for(int i=1;i<=noOfTabs;i++){
			driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
			Thread.sleep(200);
			}
			for(int j=1;j<=noOfEnters;j++){
			driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
			Thread.sleep(100);
			}
		}
		logger.info("User Cache Cleared");
	}

public void clearCacheFirefox(int noOfTabs,int noOfEnters) throws InterruptedException
{
	//Thread.sleep(3000);
	String originalHandle = driver.getWindowHandle();
	driver.switchTo().window(originalHandle);
	Thread.sleep(2000);
	driver.get("about:preferences#privacy");
	Thread.sleep(3000);
	
	if (driver.getTitle().contains("Settings"))
	{
		for(int i=1;i<=noOfTabs;i++){
		driver.findElement(By.id("preferences-body")).sendKeys(Keys.TAB);
		Thread.sleep(200);
		}
		for(int j=1;j<=noOfEnters;j++){
		driver.findElement(By.id("preferences-body")).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		}
		alertClose();
	}
	logger.info("User Cache Cleared");
	Thread.sleep(2000);
}

//-------------------LOGIN MAIL-------------------//

public void logingMail(String user,String pwd) throws InterruptedException{

	LoginMailPage lp=new LoginMailPage(driver);
	driver.get(baseURL);
	
	if(driver.findElements(By.linkText("Sign in")).size() > 0){
	lp.clickSignin(30);
	}
	
	lp.setUserName(user,30);
	logger.info("User ID : "+user);
	
	lp.staySignIn(30);
	
	lp.clickNext(30);
	
	lp.setPassword(pwd,30);
	logger.info("User Password : ********");
	
	lp.clickSubmit(30);
	
		if (driver.getTitle().contains(user)){
		
			Assert.assertTrue(true);
			logger.info(user+" Logged in!");
		
		}
		else{
		lp.skipMessenger(30);
			if (driver.getTitle().contains(user))
			{
			Assert.assertTrue(true);
			logger.info(user+" Logged in!");
			}
			else
			Assert.assertTrue(false);
			logger.warn(user+"Log in Failed");
		}

	}
//-------------------LOG OFF ------------------//

	public void logOut(String user){
	
		LoginMailPage lp=new LoginMailPage(driver);
		lp.clickProfile(30);
		lp.clickLogout(30);
		logger.info(user+" User logged Off");
	}

	//-------------------SETTING VPN ------------------//
	public void setVpn(String city) {
	
	logger.info("VPN Location : "+city);
	
		try{
		String[] parms = {"wscript", launchVpn, city};
		Runtime.getRuntime().exec(parms);
		
		}catch (IOException e){
			
		e.printStackTrace();
		
		}
	}
	
	//-------------------CLOSE VPN-------------------//
	public void closeVpn() {	
		try {
		String[] parms1 = {"wscript",exitVpn};
		Runtime.getRuntime().exec(parms1);
		
		} catch (IOException e) {
		e.printStackTrace();
		
		}
	}

	//-------------------OPEN CLICK -------------------//
	
	public void openClick() throws InterruptedException {
	
	LoginMailPage lp=new LoginMailPage(driver);
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		if(driver.findElements(By.partialLinkText("Know More")).size() > 0){
			lp.clickKnowMore(30);
			
			defaulttab();
			
			moveTofolder();
		
		}
		else if(driver.findElements(By.partialLinkText("KNOW MORE")).size() > 0){
			lp.clickKNOWMore(30);
			
			defaulttab();
			
			moveTofolder();
			
		}
		else if(driver.findElements(By.partialLinkText("READ MORE")).size() > 0){
			lp.clickREADMore(30);
			
			defaulttab();
			
			moveTofolder();
			
		}
		else {
			moveTofolder();
		}
	}

	public void moveTofolder() throws InterruptedException {
		
		LoginMailPage lp=new LoginMailPage(driver);
		
		lp.moveToFolder(30);
			
		Thread.sleep(2000);
		
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		if(driver.findElements(By.id("26")).size() > 0){
		
			driver.findElement((By.id("26"))).click();
			
		}
		else{
			
		driver.findElement((By.id("27"))).click();
		
		}
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}

	//-------------------ALERTS-------------------//
	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}//try
		catch(Exception e){
			return false;
		}//catch
	
	}

	public void alertClose() {
	
		if (isAlertPresent()) {
			driver.switchTo().alert();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}


}

package com.YahooMail.testCases;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.YahooMail.pageObjects.LoginMailPage;

public class TC_Testing extends BaseClass{

	@Test(dataProvider="LoginData")
	public void Tesing_new(String user,String pwd,String city) throws InterruptedException{
	
		userName=user;
		cityName=city;


		LoginMailPage lp=new LoginMailPage(driver);
		//-------------------SET VPN-------------------//
		
		setVpn(city);
		Thread.sleep(30000);
		
		//-------------------LOG IN -------------------//
		
		logingMail(user,pwd);
		logger.info(user+" logged to city : "+city);
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		
			
		//-------------------SPAM FOLDER -------------------//
		lp.clickSpam(30);

		driver.navigate().refresh();

		// Spam mails More than 3
		while(lp.moreThanThreeMails_check()>0){

						lp.moreThanThreeMails_click(30);

						// Click on Show images link
						alertClose();
						Thread.sleep(1000);
						
						lp.clickShowImage(30);
						Thread.sleep(3000);
						
						alertClose();
						lp.clickNotSpam(30);
						
						alertClose();
						Thread.sleep(1000);
						
						driver.navigate().refresh();
						Thread.sleep(1000);
						alertClose();

					}


		// Spam mails less than 3

		while(lp.lessThanThreeMails_check()>0){

						lp.lessThanThreeMails_click(30);
						
						// Click on Show images link
						alertClose();
						Thread.sleep(1000);
						
						lp.clickShowImage(30);
						Thread.sleep(3000);
						
						alertClose();
						lp.clickNotSpam(30);
						
						alertClose();
						Thread.sleep(1000);
						
						driver.navigate().refresh();
						Thread.sleep(1000);
						alertClose();

					}	


		logger.info(user+" : All Spam mails are moved to Inbox");



		//-------------------INBOX--------------------//

		
		lp.clickInbox(30);

		driver.navigate().refresh();

		// Inbox More than 3 Mails
		while(lp.moreThanThreeMails_check() >0){

				lp.moreThanThreeMails_click(30);

				openClick();
				Thread.sleep(3000);
				
				alertClose();
				lp.clickInbox(30);
				
				driver.navigate().refresh();
				Thread.sleep(1000);
				alertClose();



				}


		// Inbox less than 3 Mails
		while(lp.lessThanThreeMails_check() >0){

			lp.lessThanThreeMails_click(30);
			
			openClick();
			Thread.sleep(3000);
			
			
			alertClose();
			lp.clickInbox(30);
			
			driver.navigate().refresh();
			Thread.sleep(1000);
			alertClose();


			}

				logger.info(user+" : All Inbox mails are read");

				logOut(user);		

		}


	//-------------------CACHE CLEAR-------------------//


	@AfterMethod
	public void settingBack() throws InterruptedException, IOException{

			
			try{

				if(BaseClass.browserName.equals("chrome"))	
					{
					
						clearCacheChrome(7, 1);
					}	
				if(BaseClass.browserName.equals("firefox"))	
					{	
					clearCacheFirefox(10,2);

					}
			}
				catch(Exception e){
							logger.info("Failed in Cache Cleared");
					}
			
				closeVpn();
	
				Thread.sleep(25000);


			}

}


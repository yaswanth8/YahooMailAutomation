package com.YahooMail.testCases;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.YahooMail.pageObjects.LoginMailPage;

public class TC_Testing_01 extends BaseClass{

	@Test(dataProvider="LoginData",retryAnalyzer = com.YahooMail.utilities.Retry.class)
	public void Tesing_new(String user,String pwd,String city) throws InterruptedException{
	
		userName=user;
		cityName=city;
		int spam_count=0,inbox_count=0;
		

		LoginMailPage lp=new LoginMailPage(driver);
		//-------------------SET VPN-------------------//
		
		setVpn(city);
		Thread.sleep(30000);
		
		//-------------------LOG IN -------------------//
		
		logingMail(user,pwd);
		logger.info(user+" logged to city : "+city);
		
		
		
		//-------------------SPAM FOLDER -------------------//
		lp.clickSpam(30);

		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
        Thread.sleep(3000);
        
        alertClose();
        
		if(lp.emptyFolder()>0) {
			logger.info(user+" : Spam Folder is Already Empty");
		}
		else {
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		// Spam mails More than 3
		while(lp.moreThanThreeMails_check()>0){
			
						spam_count++;
						
						if(spam_count==Num_of_mails) {
							logger.info("Spam mails target reached : "+spam_count+" mails");
							break;
						}
			
						alertClose();

						lp.moreThanThreeMails_click(30);

						// Click on Show images link
						alertClose();
						Thread.sleep(1000);
						
						lp.clickShowImage(30);
						Thread.sleep(3000);
						
						alertClose();
						lp.clickNotSpam(30);
						
						
						Thread.sleep(1000);
						alertClose();
						Thread.sleep(1000);
						
						driver.navigate().refresh();
						Thread.sleep(2000);
						alertClose();

					}


		// Spam mails less than 3

		while(lp.lessThanThreeMails_check()>0){
			
						spam_count++;
						
						if(spam_count==Num_of_mails) {
							logger.info("Spam mails target reached : "+spam_count+" mails");
							break;
						}

						lp.lessThanThreeMails_click(30);
						
						// Click on Show images link
						alertClose();
						Thread.sleep(1000);
						
						lp.clickShowImage(30);
						Thread.sleep(3000);
						
						alertClose();
						lp.clickNotSpam(30);
						
						
						Thread.sleep(1000);
						alertClose();
						Thread.sleep(1000);
						
						driver.navigate().refresh();
						Thread.sleep(2000);
						alertClose();

					


		logger.info(user+" : All Spam mails are moved to Inbox");
		
		}

		}

		//-------------------INBOX--------------------//

		
		lp.clickInbox(30);

		driver.navigate().refresh();
		
		Thread.sleep(3000);
		
		alertClose();

		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		if(lp.emptyFolder()>0) {
			logger.info(user+" : Inbox Folder is Already Empty");
		}
		else {
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			// Inbox More than 3 Mails
			while(lp.moreThanThreeMails_check() >0){
				
					inbox_count++;
					
					if(inbox_count==Num_of_mails) {
						logger.info("inbox mails target reached : "+spam_count+" mails");
						break;
					}
	
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
				
				inbox_count++;
				
				if(inbox_count==Num_of_mails) {
					logger.info("Inbox mails target reached : "+spam_count+" mails");
					break;
				}
	
				lp.lessThanThreeMails_click(30);
				
				openClick();
				Thread.sleep(3000);
				
				
				alertClose();
				lp.clickInbox(30);
				
				driver.navigate().refresh();
				Thread.sleep(1000);
				alertClose();
	
	
				
	
					logger.info(user+" : All Inbox mails are read");
					
			}		
					
		}		

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


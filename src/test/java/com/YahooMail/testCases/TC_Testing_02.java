package com.YahooMail.testCases;

import java.io.IOException;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TC_Testing_02 extends BaseClass{
	
	@Test(dataProvider="LoginData",retryAnalyzer = com.YahooMail.utilities.Retry.class)
	public void Tesing_new(String user,String pwd,String city) throws InterruptedException{
	
		userName=user;
		cityName=city;

		//-------------------SET VPN-------------------//
		
		setVpn(city);
		Thread.sleep(30000);
		
		//-------------------LOG IN -------------------//
		
		logingMail(user,pwd);
		logger.info(user+" logged to city : "+city);
		
		//-------------------LOG OFF -------------------//
		
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





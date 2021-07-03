package com.YahooMail.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src=new File("./Configurations/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro =new Properties();
			pro.load(fis);
			
		}
		catch(Exception e)
		{
			System.out.println(" Exception is "+e.getMessage());
		}
	}
	
	
	public String getAppliactionURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername()
	{
		String username=pro.getProperty("username");
		return username;
	}
	
	public String getpassword()
	{
		String password=pro.getProperty("password");
		return password;
	}
	
	public String getChromepath()
	{
		String Chromepath=pro.getProperty("chromepath");
		return Chromepath;
	}
	public String getFirefoxpath()
	{
		String Firefoxpath=pro.getProperty("firefoxpath");
		return Firefoxpath;
	}
	
	public String getChromeclearDatapath()
	{
		String Chromecleardatapath=pro.getProperty("chromeclearBrowserdata");
		return Chromecleardatapath;
	}
	public String getLaunchVpn()
	{
		String LaunchVpnPath=pro.getProperty("launchVpn");
		return LaunchVpnPath;
	}
	public String getexitVpn()
	{
		String exitVpnPath=pro.getProperty("exitVpn");
		return exitVpnPath;
	}

}


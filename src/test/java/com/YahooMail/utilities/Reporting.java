package com.YahooMail.utilities;

//Listener class used to extend reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.YahooMail.testCases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());//time stamp
		String repName="Report_"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user"," Automation Engineer ");
		
		htmlReporter.config().setDocumentTitle("Yahoo Mail Automation"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(BaseClass.userName); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(BaseClass.cityName,ExtentColor.GREEN)); // send the passed information to the report with GREEN colour highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(BaseClass.userName); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(BaseClass.cityName,ExtentColor.RED)); // send the passed information to the report with GREEN colour highlighted
		try {
			takeScreenshot();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(BaseClass.userName); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(BaseClass.cityName,ExtentColor.ORANGE));
		try {
			takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	public void takeScreenshot() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" +BaseClass.userName+ ".png");
		FileUtils.copyFile(source, target);
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+BaseClass.userName+".png";
		logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		
	}
}
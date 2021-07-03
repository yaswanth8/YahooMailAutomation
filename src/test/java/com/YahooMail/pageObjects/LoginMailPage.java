package com.YahooMail.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMailPage {

WebDriver ldriver;

public LoginMailPage(WebDriver rdriver)
{
ldriver=rdriver;
PageFactory.initElements(rdriver, this);
}

String btnSignin_linkText ="Sign in";

String txtUserName_xpath="//*[@id=\'login-username\']";

String staySignin_css="span.stay-signed-in";

String btnNext_id="login-signin";

String txtPassword_id="login-passwd";

String btnLogin_id="login-signin";

String btnskip_messenger_xpath="//*[@id=\"mail-app-component\"]/div[2]/div[1]";

String btnUnread_linkText ="Unread";

String btnSpam_css ="[title*='Spam']";

String btnInbox_css ="[title*='Inbox']";

String empty_folder_xpath="//body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]";

String btnMoveToFolder_xpath="//span[contains(text(),'Move')]";

String btnApplynow_partialLinkText ="Apply Now";

String btnKnowMore_partialLinkText ="Know More";

String btnProfile_xpath="/html/body/header/div/div/div[2]/div/div[3]/div[1]/div/label/div/img";

String btnLogout_xpath="/html/body/header/div/div/div[2]/div/div[3]/div[1]/div/div/div/a[3]";

String NumUnread_xpath="/html/body/div[1]/div/div[1]/div/div[2]/div/div[1]/nav/div/div[3]/div[1]/ul/li[1]/div/a/span[2]/span";

String linkShowImage_xpath="//span[contains(text(),'Show images')]";

String btnNotSpam_xpath="//span[contains(text(),'Not spam')]";

String moreThanThreeMails="//*[@id=\"mail-app-component\"]/div[1]/div/div[2]/div/div/div[3]/div/div[1]/ul/li[3]/a";

String lessThanThreeMails="//*[@id=\"mail-app-component\"]/div[1]/div/div[2]/div/div/div[2]/div/div[1]/ul/li[2]/a";

public void clickSignin(int sec){

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.linkText(btnSignin_linkText)));
ldriver.findElement(By.linkText(btnSignin_linkText)).click();
}

public void setUserName(String uname,int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(txtUserName_xpath)));
ldriver.findElement(By.xpath(txtUserName_xpath)).sendKeys(uname);;
}

public void staySignIn(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(staySignin_css)));
ldriver.findElement(By.cssSelector(staySignin_css)).click();
}

public void clickNext(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.id(btnNext_id)));
ldriver.findElement(By.id(btnNext_id)).click();
}

public void setPassword(String pwd,int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.id(txtPassword_id)));
ldriver.findElement(By.id(txtPassword_id)).sendKeys(pwd);
}

public void clickSubmit(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.id(btnLogin_id)));
ldriver.findElement(By.id(btnLogin_id)).click();
}

public void skipMessenger(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnskip_messenger_xpath)));
ldriver.findElement(By.xpath(btnskip_messenger_xpath)).click();
}

public void clickSpam(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnSpam_css)));
ldriver.findElement(By.cssSelector(btnSpam_css)).click();
}

public void clickInbox(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnInbox_css)));
ldriver.findElement(By.cssSelector(btnInbox_css)).click();
}

public void moveToFolder(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnMoveToFolder_xpath)));
ldriver.findElement(By.xpath(btnMoveToFolder_xpath)).click();
}

public void clickApplynow(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(btnApplynow_partialLinkText)));
ldriver.findElement(By.partialLinkText(btnApplynow_partialLinkText)).click();
}

public void clickKnowMore(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(btnKnowMore_partialLinkText)));
ldriver.findElement(By.partialLinkText(btnKnowMore_partialLinkText)).click();
}

public void clickShowImage(int sec) {

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(linkShowImage_xpath)));
ldriver.findElement(By.xpath(linkShowImage_xpath)).click();
}


public void clickProfile(int sec)
{
WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnProfile_xpath)));
ldriver.findElement(By.xpath(btnProfile_xpath)).click();
}

public void clickLogout(int sec)
{
WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnProfile_xpath)));
ldriver.findElement(By.xpath(btnLogout_xpath)).click();
}


public void clickNotSpam(int sec)
{
WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnNotSpam_xpath)));
ldriver.findElement(By.xpath(btnNotSpam_xpath)).click();
}


public int numberUnreadMails()
{
int NumberOfMails;


String numOfMails=ldriver.findElement(By.xpath(NumUnread_xpath)).getText();

NumberOfMails=Integer.parseInt(numOfMails);  

return NumberOfMails;
}

public int moreThanThreeMails_check(){

int count=ldriver.findElements(By.xpath(moreThanThreeMails)).size();
return count;
}
public int lessThanThreeMails_check(){

int count=ldriver.findElements(By.xpath(lessThanThreeMails)).size();
return count;
}
public void moreThanThreeMails_click(int sec){

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(moreThanThreeMails)));
ldriver.findElement(By.xpath(moreThanThreeMails)).click();

}

public void lessThanThreeMails_click(int sec){

WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(sec));
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lessThanThreeMails)));
ldriver.findElement(By.xpath(lessThanThreeMails)).click();

}

public int emptyFolder() {
	
	int count=ldriver.findElements(By.xpath(empty_folder_xpath)).size();
	return count;
	
}




}

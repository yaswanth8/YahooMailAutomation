package com.YahooMail.utilities;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.YahooMail.testCases.BaseClass;

public class sendMail {
	
	String gmailID="xyz@gmail.com";
	String gmailPwd="xyz";	
	String[] recipients={"vamsi.4123@gmail.com","vikasnaidu15@gmail.com","saireddy26@gmail.com"};

	public void sendNormalMail(String status) throws EmailException
	{
	Email email = new SimpleEmail();
	email.setHostName("smtp.gmail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator(gmailID,gmailPwd));
	email.setSSLOnConnect(true);
	email.setFrom(gmailID, "Yahoo Automation");
	email.setSubject(BaseClass.userName+" || "+"City : "+BaseClass.cityName);
	email.setMsg(BaseClass.userName+" || "+"City : "+BaseClass.cityName);
	email.setMsg(BaseClass.userName+" || "+"City : "+BaseClass.cityName+" is "+status);
	for(int i=0;i<recipients.length;i++)
	{
		email.addTo(recipients[i]);
	}	
	email.setBounceAddress("vamsi.4123@gmail.com");
	email.send();
	}
	
	public  void sendAttachment() throws EmailException {
		 EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(System.getProperty("user.dir")+ "/test-output/"+BaseClass.repName);
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Report");
		  attachment.setName(BaseClass.repName);

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator(gmailID,gmailPwd));
		  email.setSSLOnConnect(true);
		  for(int i=0;i<recipients.length;i++)
			{
				email.addTo(recipients[i]);
			}	
		  email.setFrom(gmailID, "Yahoo Automation Report");
		  email.setSubject(BaseClass.repName);
		  email.setMsg(" Final E2E Report ");

		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
	}
}

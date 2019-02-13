package genericLibrary;
import java.io.File;

import org.apache.commons.mail.*;
public class EmailSetUp {
	public static void sentEmail() throws Exception {
		  // Create the attachment

		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(CommonUtil.getTestExecutionReportFilePath());
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Automation Test Execution Result");
		  attachment.setName("My Application");

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(25);
		  email.setAuthenticator(new DefaultAuthenticator("email@gmail.com", "***********"));
		  email.setSSLOnConnect(true);
		  email.setFrom("email@gmail.com");
		  email.setSubject("Raptor Test Execution");
		  email.setMsg("Automation Test Result");
		  email.addTo("stackholder1@gmail.com");
		  email.addTo("stackholder2@gmail.com");

		  boolean flag=true;
		  while(flag)
		  {
		  try
			{
				if((new File(CommonUtil.getTestExecutionReportFilePath()).exists()))
				{
					// add the attachment
					email.attach(attachment);
					// send the email
					email.send();
					AppLogger.log.info("Email sent Successfully");
					flag=false;
					break;
				}
			}
			catch(Exception EX)
			{
				TestUtil.delayBy(1);
				AppLogger.log.info("Exception occured during checking Report file Existance."+ EX);
				AppLogger.log.info("Could not able to sent email due to some issue");
			}
		  }
		}
		
}


package models;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaEmail {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	public JavaEmail() {
		
	}
	
	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String email, String password) throws AddressException,
			MessagingException {
		String[] toEmails = { email };
		String emailSubject = "Forget Password";
		String emailBody = "<h3>This is an email sent by Project Management System</h3>"
				+ "<p>Your password is : " + password + "</p>"
				+ "<div><a href='http://localhost:8080/Project_Management_System_Final/index.jsp' target='_blank'>Login here</a></div>";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email
		
	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "testmail6898.ds";//just the id alone without @gmail.com
		String fromUserEmailPassword = "Dheeraj6898";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

}

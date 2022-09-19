package src.net.codlin.sms;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MAIL003 {

	private MAIL003(){}
	
	public static void sendEmail(String to, String cc, String from, String subject, String text, String smtpHost) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtpHost);
			Session emailSession = Session.getDefaultInstance(properties);

			Message emailMessage = new MimeMessage(emailSession);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			emailMessage.setFrom(new InternetAddress(from));
			emailMessage.setSubject(subject);
			emailMessage.setText(text);

			emailSession.setDebug(true);

			Transport.send(emailMessage);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		try{
		String mailSmtpHost = "mail.carsa.com.py";

		String mailTo = "vtorres@carsa.com.py";
		String mailCc = "";
		String mailFrom = "hostmaster@carsa.com.py";
		String mailSubject = "SMS Recibido de 2386";
		String mailText = "SMS Recibido de 2386.....";

		sendEmail(mailTo, mailCc, mailFrom, mailSubject, mailText, mailSmtpHost);
		}
		catch(Exception ex)
		{
			
		}
	}

}
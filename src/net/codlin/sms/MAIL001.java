package src.net.codlin.sms;

import java.util.Properties;

import javax.mail.*;
//import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.lang.String;
//import java.io.*;


public class MAIL001 {

	public MAIL001() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute(String mailTo, String mailAsunto, String mailMensaje)
	{
//		package com.mkyong.common;
		System.out.println("Enviando Correo Electronico...");

//		public class SendMailTLS {
//
//			public static void main(String[] args) {

//				final String username = "username@gmail.com";
//				final String password = "password";

				Properties props = new Properties();
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp-relay.sendinblue.com");
				props.put("mail.smtp.port", "587");

				String username = "youccc123carlos@gmail.com";
				String password = "OGxS8zfLINy3K0kP";


//				Session session = Session.getInstance(props,
//				  new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username, password);
//					}
//				  });
				Session session = Session.getInstance(props);
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("youccc123carlos@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(mailTo));
					message.setSubject(mailAsunto);
					message.setText(mailMensaje);

					Transport.send(message, username, password);

					System.out.println("Email enviado con EXITO.");

				} catch (MessagingException e) {
					System.out.println("Error..");
					throw new RuntimeException(e);
				}
//			}
//		}

	}
	
	public static void main(String[] args) {
	    System.out.println("Test");
	    MAIL001 mail = new MAIL001();
	    mail.execute("youccc123carlos@gmail.com","Correo de Prueba","Este es un correo de prueba");
	    System.out.println("Done�.");
	}
}

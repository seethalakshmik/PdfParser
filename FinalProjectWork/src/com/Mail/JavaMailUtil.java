package com.Mail;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

public class JavaMailUtil {
	public static void sendMail(String recipient) throws Exception {

		System.out.println("Preparing to send email");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.user", "usoft286@gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		final String myAccountEmail = "usoft286@gmail.com";
		final String password = "DataBase2242!@";

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message = prepareMessage(session, myAccountEmail, recipient);

		/*
		 * Transport transport = session.getTransport("smtps");
		 * transport.connect("smtp.gmail.com", 465, myAccountEmail, password);
		 * transport.sendMessage(message, message.getAllRecipients());
		 * transport.close();
		 */
		Transport.send(message);

		System.out.println("Message sent");
	}
	
	static String subject = "Status Update regarding invoice";
	static String text = "Dear Vendor your invoice has been approved";

	private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText("Dear Vendor your invoice has been approved");
			return message;
		} catch (MessagingException ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	public static String getHeaderValue(){
		return subject;
	}
	public static String getText(){
		return text;
	}

}

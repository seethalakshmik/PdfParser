package com.Mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

public class FetchAttachments {

	private static String saveDirectory = "/home/content"; 
	
	public void setSaveDirectory(String dir) {
		FetchAttachments.saveDirectory = dir;
		//System.out.println("directory saved");
	}

	public void downloadEmailAttachments() throws InterruptedException {
		// System.out.println("download process started");
		Properties properties = new Properties();

		String host = "pop.gmail.com";
		String port = "995";
		String userName = "usoft286@gmail.com";
		String password = "DataBase2242!@";

		// server setting
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);
		properties.put("mail.pop3.starttls.enable", "true");

		// SSL setting
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("pop3s");
			store.connect("pop.gmail.com", userName, password);
			// opens the inbox folder
			
			System.out.println("Reading inbox");
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();
			System.out.println("Total Message --> " + arrayMessages.length);

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
			    String from = fromAddress[0].toString();
				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(saveDirectory + File.separator + fileName);
							// System.out.println("file saved in directory");
							//ConvertToString.convertToText(fileName, from);
							
							startThreads(fileName,from);

						} else {
							// this part may be the message content
							messageContent = part.getContent().toString();
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}
			}
			System.out.println("Please wait for some time...");
			Thread.sleep(3000);
			// disconnect
			folderInbox.close(false);
			store.close();
		
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();

		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();

		} catch (IOException ex) {
			ex.printStackTrace();

		}
		
	}

	private void startThreads(String fileName, String from) {
		
		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
		emailExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					ConvertToString.convertToText(fileName, from);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		emailExecutor.shutdown();

		
	}

}
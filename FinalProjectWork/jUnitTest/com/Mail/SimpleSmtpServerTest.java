package com.Mail;

import com.bussinesslogic.SendEmail;

import junit.framework.TestCase;

public class SimpleSmtpServerTest extends TestCase {
   
	public void testEmail() throws Exception{
		SendEmail.sendMail("seethalakshmik19@gmail.com");
		assertTrue(SendEmail.getHeaderValue().equals("Status Update regarding invoice"));
		assertTrue(SendEmail.getText().equals("Dear Vendor your invoice has been approved"));
	}
	
}

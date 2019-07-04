package com.Mail;

import junit.framework.TestCase;

public class SimpleSmtpServerTest extends TestCase {
   
	public void testEmail() throws Exception{
		JavaMailUtil.sendMail("seethalakshmik19@gmail.com");
		assertTrue(JavaMailUtil.getHeaderValue().equals("Status Update regarding invoice"));
		assertTrue(JavaMailUtil.getText().equals("Dear Vendor your invoice has been approved"));
	}
	
}

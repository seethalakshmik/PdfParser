package com.mainClass;
import com.Mail.*;

public class MainRunnerClass {
	public static void main(String args[]) throws Exception {
		System.out.println("Inside fetch attachments");
		FetchAttachments fa = new FetchAttachments();
		fa.setSaveDirectory("D:\\new downloaded mails");
		fa.downloadEmailAttachments();
	}
}

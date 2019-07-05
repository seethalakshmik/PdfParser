package com.api;
import java.util.Scanner;

import com.bussinesslogic.*;
import com.databackend.DisplayInvoice;
import com.databackend.StatusUpdate;

public class MainRunnerClass {
	public static void main(String args[]) throws Exception {
		System.out.println("Process starts...");
		System.out.println();
		FetchAttachments fa = new FetchAttachments();
		fa.setSaveDirectory("D:\\new downloaded mails");
		fa.downloadEmailAttachments();
		
		
		System.out.println("Ivoice details");
		DisplayInvoice.display();
		
		
		System.out.println("Do u need to approve any invoice?" + "(y/n)");
		Scanner sc = new Scanner(System.in);
		String choice = null;
		choice = sc.nextLine();
		
		if(choice.equals("y")){
		String invoice_no;
		String status;
		System.out.println("Enter the invoice number which status to be updated");
		invoice_no = sc.nextLine();
		System.out.println("Enter the status");
		status = sc.nextLine();
		StatusUpdate.UpdatingStatus(status, invoice_no);
		}
		else{
			System.out.println("Thank you for your response");
		}
		
		sc.close();
		
	}
}

package com.mainClass;


import com.finaljdbc.*;

import java.util.Scanner;

public class UpdateMain {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String invoice_no;
		String status;
		System.out.println("Enter the invoice number which status to be updated");
		invoice_no = sc.nextLine();
		System.out.println("Enter the status");
		status = sc.nextLine();
		StatusUpdate.UpdatingStatus(status, invoice_no);
		DisplayInvoice.display();
		sc.close();
	}

}

package com.Mail;

import com.finaljdbc.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class readLineFromText {

	public static void storeInDB(String Email, String FileName) {
		String invoice_no = null;
		String invoice_date = null;
		String customer_po = null;
		String customer_address = null;
		String total_amount = null;
		String status = "Not Addressed";
		String email = Email;
		String fileName = FileName;
		
		try {

			try (Stream<String> all_lines = Files.lines(Paths.get(fileName))) {
				invoice_no = all_lines.skip(6).findFirst().get();
			}

			try (Stream<String> all_lines = Files.lines(Paths.get(fileName))) {
				invoice_date = all_lines.skip(8).findFirst().get();
			}
			try (Stream<String> all_lines = Files.lines(Paths.get(fileName))) {
				customer_po = all_lines.skip(20).findFirst().get();
			}
			try (Stream<String> all_lines = Files.lines(Paths.get(fileName))) {
				customer_address = all_lines.skip(24).findFirst().get();
			}
			try (Stream<String> all_lines = Files.lines(Paths.get(fileName))) {
				total_amount = all_lines.skip(88).findFirst().get();
			}

		} catch (IOException ee) {
			System.out.println(ee);
		}
		// System.out.println(" The specific Line is: " + text);
		JdbcPreparedStatement.JdbcPreparedStatementMethod(invoice_no, invoice_date, customer_po, customer_address,
				total_amount, status, email);
	}
}

package com.finaljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayInvoice {

	public static void display() {
		Connection con = null;
		try {

			System.out.println("jdbc Connection :");

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice", "root", "seetha"); //

			System.out.println(con);

			String sql = "select * from invoice_details";
			PreparedStatement preparedStatement2 = con.prepareStatement(sql);
			ResultSet rs = preparedStatement2.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Invoice no : " + rs.getString(1));
				System.out.println("Invoice Date : " + rs.getString(2));
				System.out.println("Customer Po : " + rs.getString(3));
				System.out.println("Address : " + rs.getString(4));
				System.out.println("Total Amount : " + rs.getString(5));
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			try {
				if (con != null) {
					System.out.println("Successful");
				}
			}

			catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Connection failed!!!");
			}
		}
	}

}

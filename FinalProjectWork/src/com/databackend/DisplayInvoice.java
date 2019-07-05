package com.databackend;

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
			System.out.print("Invoice no" + "   ");
			System.out.print("Invoice Date" +  "   ");
			System.out.print("Customer Po" + "   ");
			System.out.print("       Address" + "   ");
			System.out.print("                 Total Amount");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			while (rs.next()) {
				System.out.print(rs.getString(1) + "     ");
				System.out.print(rs.getString(2) + "      ");
				System.out.print(rs.getString(3) + "     ");
				System.out.print(rs.getString(4) + "        ");
				System.out.print(rs.getString(5));
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------------------------------------------------");
				//System.out.println("----------------------------------------------------------------------------------------------------------------------------");
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

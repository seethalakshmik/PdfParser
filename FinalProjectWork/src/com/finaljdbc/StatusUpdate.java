package com.finaljdbc;

import com.Mail.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatusUpdate {
	public static void UpdatingStatus(String status, String invoice_no) {
		String StatusToUpdate = status;
		String invoiceToChange = invoice_no;
		Connection con = null;
		try {
			// System.out.println("jdbc Connection :");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice", "root", "seetha"); //

			String sql = "UPDATE invoice_details SET status = ? WHERE invoice_no = ?";
			String sql1 = "select email from invoice_details where invoice_no = " + invoiceToChange;
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, StatusToUpdate);
			preparedStatement.setString(2, invoiceToChange);
			preparedStatement.executeUpdate();
			System.out.println("update success");

			PreparedStatement preparedStatement2 = con.prepareStatement(sql1);
			ResultSet rs = preparedStatement2.executeQuery(sql1);

			while (rs.next()) {
				String email = rs.getString("email");
				System.out.println(email);
				// JavaMailUtil.sendMail(email);
				startThreads(email);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			try {
				if (con != null) {
					System.out.println("Successful");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Connection failed!!!");
			}
		}

	}

	private static void startThreads(String email) {
		// TODO Auto-generated method stub
		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
		emailExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					// SendEmailUtility.sendmail(emaildummy);
					JavaMailUtil.sendMail(email);
				} catch (IOException e) {
					// logger.error("failed", e);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		emailExecutor.shutdown();

	}

}
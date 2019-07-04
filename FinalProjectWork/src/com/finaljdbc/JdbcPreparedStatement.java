package com.finaljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class JdbcPreparedStatement {
	

	public static void JdbcPreparedStatementMethod(String invoice_no,String invoice_date,String customer_po,String customer_address,String total_amount,String status,String email) {
		// TODO Auto-generated method stub
		
		Connection con = null;
	    try{ 

             System.out.println("jdbc Connection :");

             Class.forName("com.mysql.jdbc.Driver"); 

             con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/invoice","root","seetha"); //  

             System.out.println(con);
             
             String sql = "INSERT INTO invoice_details (invoice_no,invoice_date,customer_PO,customer_address,total_amount,status,email)" +
            	        "VALUES (?, ?, ?,?,?,?,?)";
             
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             preparedStatement.setString(1, invoice_no);
             preparedStatement.setString(2, invoice_date);
             preparedStatement.setString(3, customer_po);
             preparedStatement.setString(4, customer_address);
             preparedStatement.setString(5, total_amount);
             preparedStatement.setString(6, status);
             preparedStatement.setString(7, email);
             preparedStatement.executeUpdate(); 
             
        }catch(Exception e){ System.out.println(e);}

        finally{
        	try{
                  if(con != null){
                        System.out.println("Successful");
                  }
             }

             catch(Exception e2){
                  e2.printStackTrace();
                  System.out.println("Connection failed!!!");
             }
        }

  }

}

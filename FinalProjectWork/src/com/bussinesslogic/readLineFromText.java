package com.bussinesslogic;

import java.io.*;

import com.databackend.*;

public class readLineFromText {

	public static void storeInDB(String Email, String FileName) throws IOException {
		String invoice_no = null;
		String invoice_date = null;
		String customer_po = null;
		String customer_address = null;
		String total_amount = null;
		String status = "Not Addressed";
		String email = Email;
		String fileName = FileName;
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line = br.readLine();

            while (line != null) {
                
                line = br.readLine();
                if (line.contains("Invoice No")) {
        			invoice_no = br.readLine();
        			invoice_no =  invoice_no.trim();       			
        		}
                
                if (line.contains("Invoice Date")) {
                	invoice_date = br.readLine();
                	invoice_date = invoice_date.trim();
                }	
                
                if (line.contains("Customer P.O.")) {
    				customer_po = br.readLine();
    				customer_po = customer_po.trim();
                }
                
                if (line.contains("Sold To")) {
    				customer_address = br.readLine();
    				customer_address = customer_address.trim();
                }
                if(line.contains("Total Invoice")){
                	br.readLine();
                	br.readLine();
                	total_amount = br.readLine();
                	total_amount = total_amount.trim();
                	break;
                }
                
            }
        } finally {
            br.close();
        }
        
		JdbcPreparedStatement.JdbcPreparedStatementMethod(invoice_no, invoice_date, customer_po, customer_address,
				total_amount, status, email);
	}
}

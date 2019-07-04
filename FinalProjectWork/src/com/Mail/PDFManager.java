package com.Mail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.finaljdbc.JdbcPreparedStatement;

public class PDFManager {
	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	private String Text;
	private String filePath;
	private File file;

	public String toText() throws IOException {
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;

		file = new File(filePath);
		parser = new PDFParser(new RandomAccessFile(file, "r")); // update for
																	// PDFBox V
																	// 2.0

		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		pdfStripper.setStartPage(0);
		// pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		pdfStripper.setEndPage(1);
		Text = pdfStripper.getText(pdDoc);
		pdDoc.close();
		return Text;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public PDDocument getPdDoc() {
		return pdDoc;
	}
	
	String invoice_no = null;
	String invoice_date = null;
	String customer_po = null;
	String address = null;
	String amount = null;
	String status = "Pending";
	String email = null;
	
	public void find_Details(String text,String from){
		email = from;
		if (Text.contains("Invoice No")) {
			  invoice_no = Text.substring(Text.indexOf("Invoice No") + 10,Text.indexOf("Invoice Date"));
			  //invoice_no.replaceAll(" ","");
			  //System.out.println(invoice_no);
			} 
		else{
			System.out.println("not found");
		}
		
		if (Text.contains("Invoice Date")) {
			  invoice_date = Text.substring(Text.indexOf("Invoice Date") + 12,Text.indexOf("Shipment Date"));
			  //invoice_date.replaceAll(" ","");
			  //System.out.println(invoice_no);
			} 
		else{
			System.out.println("not found");
		}
		
		if (Text.contains("Customer P.O.")) {
			  customer_po = Text.substring(Text.indexOf("Customer P.O.") + 13,Text.indexOf("Account No"));
			  //customer_po.replaceAll(" ","");
			  //System.out.println(invoice_no);
			} 
		else{
			System.out.println("not found");
		}
		
		if (Text.contains("Sold To")) {
			  address = Text.substring(Text.indexOf("Sold To") + 7,Text.indexOf("Ship To"));
			  //System.out.println(invoice_no);
			} 
		else{
			System.out.println("not found");
		}
		
		if (Text.contains("Total Invoice") && Text.contains("Invoices not paid")) {
				for (int i = Text.indexOf("Invoices not paid"); i > 0; i--) {
					if (Text.charAt(i) == '$') {
						amount = Text.substring(i+1, Text.indexOf("Invoices not paid"));
						return;
					}
				}
				//amount.replaceAll(" ","");
			} 
		else{
			System.out.println("not found");
		}
		
		JdbcPreparedStatement.JdbcPreparedStatementMethod(invoice_no, invoice_date, customer_po,address,amount, status, email);
	}

	public void writeTexttoFile(String text2, String path, String fileName) throws IOException {
		// TODO Auto-generated method stub

		String actualPath = path + fileName;
		System.out.println();
		System.out.println(actualPath);
		File file = new File(actualPath);

		// Create the file
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}

		// Write Content
		FileWriter writer = new FileWriter(file);
		writer.write(text2);
		writer.close();

	}
}

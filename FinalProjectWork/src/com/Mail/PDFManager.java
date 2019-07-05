package com.Mail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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
		parser = new PDFParser(new RandomAccessFile(file, "r"));
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

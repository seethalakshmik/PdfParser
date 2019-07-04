package com.Mail;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertToString {
	public static void convertToText(String fileName, String from) {
		String files = fileName;
		System.out.println(files);
		String nfiles = "D:\\new text file\\";
		PDFManager pdfManager = new PDFManager();
		pdfManager.setFilePath("D:\\new downloaded mails\\" + files);
		try {
			String text = pdfManager.toText();
			// System.out.println(text);
			//pdfManager.find_Details(text, from);
			pdfManager.writeTexttoFile(text, nfiles, files + ".txt");
			readLineFromText.storeInDB(from, nfiles + files + ".txt");

		} catch (IOException ex) {
			Logger.getLogger(PDFBoxReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}

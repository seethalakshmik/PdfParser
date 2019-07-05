package com.bussinesslogic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertToText {
	public static void convertToText(String fileName, String from) {
		String files = fileName;
		System.out.println(files);
		String nfiles = "D:\\new text file\\";
		CreateFilePath pdfManager = new CreateFilePath();
		pdfManager.setFilePath("D:\\new downloaded mails\\" + files);
		try {
			String text = pdfManager.toText();
			pdfManager.writeTexttoFile(text, nfiles, files + ".txt");
			readLineFromText.storeInDB(from, nfiles + files + ".txt");

		} catch (IOException ex) {
			Logger.getLogger(ConvertPdfToTextFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}

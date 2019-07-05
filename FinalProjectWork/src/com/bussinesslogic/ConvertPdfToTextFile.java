package com.bussinesslogic;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertPdfToTextFile {
	public static void main(String[] args) {
		String path = "D:\\downloaded emails\\";
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				System.out.println(files);
				String nfiles = "D:\\textfile\\";
				CreateFilePath pdfManager = new CreateFilePath();
				pdfManager.setFilePath("D:\\downloaded emails\\" + files);
				try {
					String text = pdfManager.toText();
					// System.out.println(text);
					pdfManager.writeTexttoFile(text, nfiles, files + ".txt");
				} catch (IOException ex) {
					Logger.getLogger(ConvertPdfToTextFile.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}
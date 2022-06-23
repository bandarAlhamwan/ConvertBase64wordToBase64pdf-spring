package com.convertBase64.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Service;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

@Service
public class Base64WithFileSystem {

	File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
	String sysRoot = homeDirectory.getAbsolutePath();
	String convertBase64nFolder = sysRoot + File.separator + "convertbase64";
	String folder = convertBase64nFolder + File.separator;
	
	String wordFileName = "word.doc";
	String pdfFileName = "pdfFile.pdf";
	

	public String getBase64PdfFromBase64Word(String base64Word) throws IOException {

		convertBase64ToWordDoc(base64Word);
		convertWordToPdf();
		String base64Pdf = convertPdfToBase64();

		return base64Pdf;
	}
	
	private void convertBase64ToWordDoc(String base64Word) {

		// Convert encodeFile to word document
		byte[] decodebytess = Base64.getDecoder().decode(base64Word);
		try {
			Files.write(Paths.get(folder, wordFileName), decodebytess);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void convertWordToPdf() throws IOException {
		// Convert the word to pdf
		File inputword = new File(folder, wordFileName);
		File outputPDF = new File(folder, "pdfFile.pdf");
		InputStream docxInputStream = null;
		OutputStream pdfOutputStream = null;

		try {
			docxInputStream = new FileInputStream(inputword);
			pdfOutputStream = new FileOutputStream(outputPDF);
			IConverter converter = LocalConverter.builder().build();
			converter.convert(docxInputStream).as(DocumentType.DOC).to(pdfOutputStream).as(DocumentType.PDFA).execute();

			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			docxInputStream.close();
			pdfOutputStream.close();
		}

	}

	private String convertPdfToBase64() {

		String valueOf = null;
		try {
			byte[] inFileBytes = Files.readAllBytes(Paths.get(folder + "pdfFile.pdf"));

			valueOf = Base64.getEncoder().encodeToString(inFileBytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueOf;
	}
}

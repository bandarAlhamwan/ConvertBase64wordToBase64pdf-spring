package com.convertBase64.service;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.convertBase64.node.ApiNode;

@Service
public class Base64WithNode {

	File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
	String sysRoot = homeDirectory.getAbsolutePath();
	String convertBase64nFolder = sysRoot + File.separator + "convertbase64";
	String folder = convertBase64nFolder + File.separator;
	
	String wordFileName = "wordFile.doc";
	String pdfFileName = "pdfFile.pdf";
	
	@Autowired ApiNode apiNode;

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
	
	public void convertWordToPdf() {
		//byte[] resource = null ;
		//byte[] inFileBytes = Files.readAllBytes(Paths.get("C:\\Users\\96650\\Desktop\\convertbase64\\word.doc"));
		Path path = Paths.get(folder +wordFileName); //folder +wordFileName
		String name = "wordFile.doc";
		String originalFileName = "wordFile.doc"; // the is file name in node
		String contentType = "application/msword";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		
		byte[] postWordFileAndRecivePdf = apiNode.postWordFile(result);
		
		try {
			Files.write(Paths.get(folder, "pdfFile.pdf"), postWordFileAndRecivePdf);
		} catch (Exception e) {
			e.printStackTrace();
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

	

	
	
	
	
	
	public String gethi() {
		String hi =null;
		try {
		 hi = apiNode.getHi();
		} catch (Exception e) {
			System.out.println("Cannot connect to node application");
			e.printStackTrace();
		}
		return hi;
		
	}
	
	public ResponseEntity<byte[]> getFile() {
		byte[] resource = null ;
		
		try {
			resource = apiNode.getPdfFile();
		} catch (Exception e) {
			System.out.println("Cannot connect to node application");
			e.printStackTrace();
		}
		
		try {
			Files.write(Paths.get(folder, "pdfFile.pdf"), resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
	            .contentLength(resource.length)
	            .contentType(MediaType.APPLICATION_PDF)
	            //below line to make it downloaded
	            //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +"bandar.pdf" + "\"")
	            .body(resource);
	}

	


}

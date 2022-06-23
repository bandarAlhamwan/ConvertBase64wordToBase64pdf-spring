package com.convertBase64.controller;

import java.io.IOException;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.convertBase64.entity.Base64Pdf;
import com.convertBase64.entity.Base64Word;
import com.convertBase64.service.Base64WithFileSystem;
import com.convertBase64.service.Base64WithNode;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class Base64Controller {

	private Base64WithFileSystem base64WithFileSystem;
	private Base64WithNode base64WithNode;
	
	@GetMapping("")
	public String gethi() {
		return base64WithNode.gethi();
	}
	
	@PostMapping("base64wordTobase64pdfWin")
	public String getEncodeWin(@RequestBody Base64Word base64Word) throws IOException {
		
		return base64WithFileSystem.getBase64PdfFromBase64Word(base64Word.getBase64word());
	}
	
	@PostMapping("base64wordTobase64pdf")
	public Base64Pdf getEncode(@RequestBody Base64Word base64Word) throws IOException {
		
		Base64Pdf base64Pdf = new Base64Pdf();
		base64Pdf.setBase64Pdf(base64WithNode.getBase64PdfFromBase64Word(base64Word.getBase64word()));
		return base64Pdf;
	}
	
	@GetMapping("file")
	ResponseEntity<?> getPDF() {
		return base64WithNode.getFile();
	    
	}
	
	
}

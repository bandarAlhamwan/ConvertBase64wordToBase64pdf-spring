package com.convertBase64.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class MockMultipartFile implements MultipartFile {

	private String namee;
	private String originalFileName;
	private String contentType;
	byte[] content;
	public MockMultipartFile(String name, String originalFileName, String contentType, byte[] content) {
		this.namee = name;
		this.originalFileName = originalFileName;
		this.contentType = contentType;
		this.content = content;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return namee;
	}

	@Override
	public String getOriginalFilename() {
		// TODO Auto-generated method stub
		return originalFileName;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		File inputword = new File("C:\\Users\\96650\\Desktop\\convertbase64\\word.doc");
		InputStream docxInputStream = null;
		docxInputStream = new FileInputStream(inputword);
		return docxInputStream;
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		// TODO Auto-generated method stub

	}

}

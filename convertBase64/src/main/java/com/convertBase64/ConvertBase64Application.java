package com.convertBase64;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.swing.filechooser.FileSystemView;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableFeignClients
public class ConvertBase64Application {

	public static void main(String[] args) {
		SpringApplication.run(ConvertBase64Application.class, args);
	}

	
	@PostConstruct
	public void createDirectory() {
		File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
		String sysRoot = homeDirectory.getAbsolutePath();
		String convertBase64nFolder = sysRoot + File.separator + "convertbase64";
		
		File file = new File(convertBase64nFolder);
		if (!file.exists()) {
			file.mkdir();
			log.info("The Directory is Creation " + file.getAbsolutePath());
		}else {
			log.info("The Directory Exist " + file.getAbsolutePath());
		}
		

	}
}

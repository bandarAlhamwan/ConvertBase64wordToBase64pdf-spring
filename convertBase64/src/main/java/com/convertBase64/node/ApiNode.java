package com.convertBase64.node;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "ApiNode" , url = "${api.node}")
public interface ApiNode {
	
	@RequestMapping(method = RequestMethod.GET ,value = "")
	String getHi();
	
	@RequestMapping(method = RequestMethod.GET ,value = "wordToPdf")
	byte[] getPdfFile();
	
	@RequestMapping(method = RequestMethod.POST, value = "upload-avatar" , headers = "Content-Type=multipart/form-data" )
	byte[] postWordFile(@RequestPart("avatar") MultipartFile file);
	
	
	


}

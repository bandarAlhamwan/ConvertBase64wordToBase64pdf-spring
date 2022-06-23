package com.convertBase64.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${api.node}")
	private String apiNode;
	
	
	@GetMapping("api")
	public String getApiNodeUrl() {
		return this.apiNode;
	}
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {
	
	@Autowired
	private AService aService;

	@GetMapping("/")
	public String getUser() {
		return aService.getUser();
	}
}

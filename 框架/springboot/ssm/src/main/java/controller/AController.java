package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.AService;

@RestController
public class AController {

	@Autowired
	private AService aService;
	
	@RequestMapping("/a")
	public String a() {
		return aService.getAById("wwww").getA();
	}
	

}

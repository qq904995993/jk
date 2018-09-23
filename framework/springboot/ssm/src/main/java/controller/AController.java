package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
public class AController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{userNo}")
	@ResponseBody
	public String a(@PathVariable("userNo") String userNo) {
		User user = userService.getUserByUserNo(userNo);
		return user == null ? "未找到用户信息" : user.getUsername();
	}
	

}

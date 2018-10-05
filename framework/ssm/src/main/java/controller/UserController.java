package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import model.User;
import service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/a")
	@ResponseBody
	public JSONObject showA() {
		User user = userService.getUserByUserNo("1");
		JSONObject json = new JSONObject();
		json.put("username", user.getUsername());
		json.put("password", user.getPassword());
		return json;
	}
	
}

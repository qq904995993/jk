package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import model.A;
import service.AService;

@Controller
@RequestMapping(value = "/")
public class AController {
	
	@Autowired
	private AService aService;
	
	@RequestMapping(value = "/a")
	@ResponseBody
	public JSONObject showA() {
		A a = aService.getAById("wwww");
		JSONObject json = new JSONObject();
		json.put("id", a.getId());
		json.put("a", a.getA());
		return json;
	}
	
}

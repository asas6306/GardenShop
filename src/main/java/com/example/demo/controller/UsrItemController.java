package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrItemController {
	
	@RequestMapping("/usr/item/list")
	@ResponseBody
	public String list(HttpServletRequest req, String group) {
		
		
		
		return "/usr/item/list";
	}
	
}

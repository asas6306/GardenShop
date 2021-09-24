package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrMainController {
	
	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main")
	public String home(HttpServletRequest req) {
		
		return "/usr/home/main";
	}
}

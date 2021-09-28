package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CarService;

@Controller
public class UsrCarContoller {
	@Autowired
	CarService cs;
	
	// https://blog.naver.com/skadlf7777/222483992202
	
	@RequestMapping("/usr/car/list")
	public String list(HttpServletRequest req, String group) {
		
		req.setAttribute("cars", cs.getCars(group)); 
		
		return "/usr/car/list";
	}
}

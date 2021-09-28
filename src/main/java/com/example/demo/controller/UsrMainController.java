package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Car;
import com.example.demo.service.CarService;

@Controller
public class UsrMainController {
	@Autowired
	CarService cs;
	
	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main")
	public String home(HttpServletRequest req) {
		
		List<Car> bestCar = cs.getBest();
		req.setAttribute("bestCar", bestCar);
		
		return "/usr/home/main";
	}
}

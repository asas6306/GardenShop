package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Car;
import com.example.demo.service.CarService;

@Controller
public class UsrCarContoller {
	@Autowired
	CarService cs;
	
	// https://blog.naver.com/skadlf7777/222483992202
	
	@RequestMapping("/usr/car/list")
	public String list(HttpServletRequest req, String group) {
		
		List<Car> cars = cs.getCars(group, "ALL");
		req.setAttribute("cars", cars);
		
		return "/usr/car/list";
	}
}
 
package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Car;
import com.example.demo.service.CarService;

@Controller
public class AdmHomeController {
	@Autowired
	CarService cs;

	@RequestMapping("/adm/home/main")
	public String home(HttpServletRequest req) {
		
		List<Car> cars = cs.getCars("all", "sales");
		
		int i = 1;
		for(Car car : cars) {
			car.getExtraNotNull().put("num", i++);
		}
		
		req.setAttribute("cars", cars);
		
		return "/adm/home/main";
	}
}

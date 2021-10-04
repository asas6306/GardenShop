package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Car;
import com.example.demo.service.CarService;
import com.example.demo.service.SimplerService;

@Controller
public class UsrCarContoller {
	@Autowired
	CarService cs;
	@Autowired
	SimplerService ss;
	
	// https://blog.naver.com/skadlf7777/222483992202
	
	@RequestMapping("/usr/car/list")
	public String list(HttpServletRequest req, @RequestParam(defaultValue = "all") String group, @RequestParam(defaultValue = "1") int page) {
		
		List<Car> cars = cs.getCars(group, "ALL");
		req.setAttribute("cars", cars);
		
		int carsCnt = cars.size(); 
		
		// 페이징
		if(carsCnt != 0) {
			int pageCnt = 15;
			page = ss.page(req, page, pageCnt, carsCnt);
			
			req.setAttribute("cars", cars);
		} else {
			req.setAttribute("cars", null);
		}
		
		return "/usr/car/list";
	}
}
 
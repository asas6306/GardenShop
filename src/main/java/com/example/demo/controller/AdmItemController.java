package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Item;
import com.example.demo.service.BasketService;
import com.example.demo.service.ItemService;
import com.example.demo.service.SimplerService;

@Controller
public class AdmItemController extends _BaseController {
	@Autowired
	ItemService is;
	@Autowired
	BasketService bs;
	@Autowired
	SimplerService ss;
	
	
	@RequestMapping("/adm/item/main")
	public String main(HttpServletRequest req) {
		
		return "/adm/item/main";
	}
	
	@RequestMapping("/adm/item/order")
	public String order(HttpServletRequest req) {
		
		List<Item> orders = is.getOrders();
		req.setAttribute("orders", orders);
		
		return "/adm/item/order";
	}
}

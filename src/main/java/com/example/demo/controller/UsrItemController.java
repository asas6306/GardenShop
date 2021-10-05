package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Item;
import com.example.demo.service.ItemService;

@Controller
public class UsrItemController {
	@Autowired
	ItemService is;
	
	
	@RequestMapping("/usr/item/list")
	public String list(HttpServletRequest req, String group) {
		
		List<Item> items = is.getItems(group);
		req.setAttribute("items", items);
		
		return "/usr/item/list";
	}
	
}

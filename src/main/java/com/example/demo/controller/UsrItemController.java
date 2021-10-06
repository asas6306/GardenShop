package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Item;
import com.example.demo.dto.Rq;
import com.example.demo.service.BasketService;
import com.example.demo.service.ItemService;

@Controller
public class UsrItemController {
	@Autowired
	ItemService is;
	@Autowired
	BasketService bs;
	
	
	@RequestMapping("/usr/item/list")
	public String list(HttpServletRequest req, @RequestParam(defaultValue = "all") String group) {
		
		List<Item> items = is.getItems(group);
		req.setAttribute("items", items);
		
		return "/usr/item/list";
	}
	
	@RequestMapping("/usr/item/basket")
	public String basket(HttpServletRequest req) {
		
		Rq rq = (Rq)req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		List<Item> items = bs.getBasketList(uid);
		
		return "/usr/item/basket";
	}
}

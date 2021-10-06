package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BasketDao;
import com.example.demo.dto.Item;

@Service
public class BasketService {
	@Autowired
	BasketDao bd;
	
	public List<Item> getBasketList(int uid) {
		
		return bd.getBasketList(uid);
	}

}

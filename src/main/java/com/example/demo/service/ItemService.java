package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemDao;
import com.example.demo.dto.Item;

@Service
public class ItemService {
	@Autowired
	ItemDao id;
	
	
	public List<Item> getItems(String group) {
		
		return id.getItems(group);
	}

}

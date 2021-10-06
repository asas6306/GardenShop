package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BasketDao;
import com.example.demo.dto.Item;
import com.example.demo.util.ResultData;

@Service
public class BasketService {
	@Autowired
	BasketDao bd;
	
	public List<Item> getBasketList(int uid) {
		
		return bd.getBasketList(uid);
	}

	public ResultData putIn(int iid, int uid) {
		
		bd.putIn(iid, uid);
		
		return new ResultData("S-1", "장바구니에 담았습니다.");
	}

}

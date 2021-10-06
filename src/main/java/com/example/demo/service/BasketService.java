package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BasketDao;
import com.example.demo.dto.GenFile;
import com.example.demo.dto.Item;
import com.example.demo.util.ResultData;

@Service
public class BasketService {
	@Autowired
	BasketDao bd;
	@Autowired
	GenFileService fs;
	
	public List<Item> getBasketList(int uid) {
		
		List<Item> items = bd.getBasketList(uid);
		
		// 해당 이미지 가져오기
		List<Integer> iids = items.stream().map(item -> item.getIid())
				.collect(Collectors.toList());
		if(!iids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("item", iids, "common", "all");
			for(Item item : items) {
				Map<String, GenFile> mapByFileNo = filesMap.get(item.getIid());
		
				if (mapByFileNo != null)
					item.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return items;
	}

	public ResultData putIn(int iid, int uid) {
		
		bd.putIn(iid, uid);
		
		return new ResultData("S-1", "장바구니에 담았습니다.");
	}

	public ResultData putOut(int bid) {
		
		bd.putOut(bid);
		
		return new ResultData("S-1", "장바구니에서 삭제되었습니다.");
	}

}

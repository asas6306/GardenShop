package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemDao;
import com.example.demo.dto.GenFile;
import com.example.demo.dto.Item;
import com.example.demo.util.ResultData;

@Service
public class ItemService {
	@Autowired
	ItemDao id;
	@Autowired
	BasketService bs;
	@Autowired
	GenFileService fs;
	
	public List<Item> getItems(String group) {
		
		List<Item> items = id.getItems(group);
		
		// 해당 이미지 가져오기
		List<Integer> iids = items.stream().map(item -> item.getIid())
				.collect(Collectors.toList());
		if(!iids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("item", iids, "common", group);
			for(Item item : items) {
				Map<String, GenFile> mapByFileNo = filesMap.get(item.getIid());
		
				if (mapByFileNo != null)
					item.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return items;
	}
	
	public int getItemsCnt(String group) {
		
		return id.getItemsCnt(group);
	}

	public Item getItemByBid(int bid) {
		
		Item item = id.getItemByBid(bid);

		// 해당 이미지 가져오기
		int iid = item.getIid();
		if(item != null) {
			GenFile file = fs.getGenFile("item", iid, "common", item.getGroup(), 0);
			item.getExtraNotNull().put("file__common__all", file);
		}
		
		
		return item;
	}

	public ResultData doOrder(int bid) {
		
		id.doOrder(bid);
		bs.putOut(bid);
		
		return new ResultData("S-1", "주문이 완료되었습니다.");
	}

	public List<Item> getItemsForPaging(String group, int page, int pageCnt) {
		
		List<Item> items = id.getItemsForPaging(group, page,pageCnt);
		
		// 해당 이미지 가져오기
		List<Integer> iids = items.stream().map(item -> item.getIid())
				.collect(Collectors.toList());
		if(!iids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("item", iids, "common", group);
			for(Item item : items) {
				Map<String, GenFile> mapByFileNo = filesMap.get(item.getIid());
		
				if (mapByFileNo != null)
					item.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return items;
	}

	public List<Item> getOrders() {
		
		List<Item> items = id.getOrders();
		
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

}

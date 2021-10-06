package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemDao;
import com.example.demo.dto.GenFile;
import com.example.demo.dto.Item;

@Service
public class ItemService {
	@Autowired
	ItemDao id;
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

}

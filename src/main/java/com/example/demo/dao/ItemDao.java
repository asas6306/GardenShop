package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Item;

@Mapper
public interface ItemDao {

	public List<Item> getItems(@Param(value = "group") String group);

	public Item getItemByBid(@Param(value = "bid") int bid);

	public void doOrder(@Param(value = "bid") int bid);

	public void deleteBasket(@Param(value = "bid") int bid);

	public int getItemsCnt(@Param(value = "group") String group);

	public List<Item> getItemsForPaging(@Param(value="group") String group, @Param(value="page") int page, @Param(value="pageCnt") int pageCnt);

	public List<Item> getOrders();
	
}

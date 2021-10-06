package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Item;

@Mapper
public interface BasketDao {

	public List<Item> getBasketList(@Param(value = ("uid")) int uid);

	public void putIn(@Param(value = "iid") int iid, @Param(value = "uid") int uid);

	public void putOut(@Param(value = "bid") int bid);

}

package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Item;

@Mapper
public interface ItemDao {

	public List<Item> getItems(@Param(value = "group")String group);
	
}
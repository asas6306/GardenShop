package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Article;

@Mapper
public interface SupportDao {

	public void reqCounsel(@Param(value="target") String target, @Param(value="year") String year, @Param(value="uid") int uid);

	public List<Article> getFAQ();

}

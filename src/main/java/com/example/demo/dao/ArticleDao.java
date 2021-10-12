package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Board;

@Mapper
public interface ArticleDao {

	public List<Board> getBoards(@Param(value = "articleType") String articleType);

	public void doAdd(@Param(value = "uid") int uid, @Param(value = "title") String title, 
			@Param(value = "body") String body, @Param(value = "group") String group, 
			@Param(value = "articleType") String articleType);

}

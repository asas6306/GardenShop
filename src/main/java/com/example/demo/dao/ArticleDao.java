package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Board;

@Mapper
public interface ArticleDao {

	public List<Board> getBoards(@Param(value = "articleType") String articleType);

}

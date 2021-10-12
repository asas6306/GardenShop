package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Board;

@Service
public class ArticleService {
	@Autowired
	ArticleDao ad;
	
	public List<Board> getBoards(String articleType) {
		
		return ad.getBoards(articleType);
	}
	
	
	
}

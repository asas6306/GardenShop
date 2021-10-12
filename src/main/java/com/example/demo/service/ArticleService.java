package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Board;
import com.example.demo.util.ResultData;

@Service
public class ArticleService {
	@Autowired
	ArticleDao ad;
	
	public List<Board> getBoards(String articleType) {
		
		return ad.getBoards(articleType);
	}

	public ResultData doAdd(int uid, String title, String body, String group, String articleType) {
		
		ad.doAdd(uid, title, body, group, articleType);
		
		return new ResultData("S-1", "게시물이 작성되었습니다.");
	}
	
	
	
}

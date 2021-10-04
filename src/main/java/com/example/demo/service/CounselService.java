package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CounselDao;
import com.example.demo.util.ResultData;

@Service
public class CounselService {
	@Autowired
	CounselDao cnsld;
	
	public ResultData reqCounsel(String target, String year, int uid) {
		
		// 실패 가정 이미 상담신청을 한 경우
		
		cnsld.reqCounsel(target, year, uid);
		
		return new ResultData("S-1", "상담 신청이 완료되었습니다.");
	}

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.CounselService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrCounselController {
	@Autowired
	CounselService cnsls;
	
	@RequestMapping("/usr/cnsl/send")
	@ResponseBody
	public String send(String target, String year, int uid) {
		
		ResultData reqCounselRd = cnsls.reqCounsel(target, year, uid);
		
		return Util.msgAndBack(reqCounselRd.getMsg());
	}
}

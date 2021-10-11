package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.SupportService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrSupportController {
	@Autowired
	SupportService sups;
	
	@RequestMapping("/usr/sup/send")
	@ResponseBody
	public String send(String target, String year, int uid) {
		
		ResultData reqCounselRd = sups.reqCounsel(target, year, uid);
		
		return Util.msgAndBack(reqCounselRd.getMsg());
	}
}

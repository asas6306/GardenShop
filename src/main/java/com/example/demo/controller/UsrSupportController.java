package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.Rq;
import com.example.demo.service.ArticleService;
import com.example.demo.service.SupportService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrSupportController extends _BaseController {
	@Autowired
	ArticleService as;
	@Autowired
	SupportService sups;
	
	@RequestMapping("/usr/sup/main")
	public String main(HttpServletRequest req, String group) {
		
		List<Article> FAQs = sups.getFAQ(group);
		req.setAttribute("FAQs", FAQs);
		
		List<Board> groups = as.getBoards("FAQ");
		req.setAttribute("groups", groups);
		
		return "usr/sup/main";
	}
	
	@RequestMapping("/usr/sup/send")
	@ResponseBody
	public String send(String target, String year, int uid) {
		
		ResultData reqCounselRd = sups.reqCounsel(target, year, uid);
		
		return Util.msgAndBack(reqCounselRd.getMsg());
	}
	
	@RequestMapping("/usr/sup/addFAQ")
	public String addFAQ(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		if(!rq.isAdmin())
			msgAndBack(req, "작성 권한이 없습니다.");
		
		List<Board> boards = as.getBoards("FAQ");
		req.setAttribute("boards", boards);
		
		return "usr/sup/addFAQ";
	}
	
	@RequestMapping("/usr/sup/doAddFAQ")
	@ResponseBody
	public String doAddFAQ(HttpServletRequest req, String group, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		ResultData doAddFAQRd = as.doAdd(uid, title, body, group, "FAQ");
		
		return Util.msgAndReplace(doAddFAQRd.getMsg(), "main");
	}
	
	@RequestMapping("/usr/sup/inquire")
	public String inquire(HttpServletRequest req) {
		
		List<Board> boards = as.getBoards("FAQ");
		req.setAttribute("boards", boards);
		
		return "usr/sup/inquire";
	}
	
	@RequestMapping("/usr/sup/doInquire")
	@ResponseBody
	public String doInquire(HttpServletRequest req, String group, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		ResultData doAddInquireRd = as.doAdd(uid, title, body, group, "inquire");
		
		return Util.msgAndReplace(doAddInquireRd.getMsg(), "main");
	}
}

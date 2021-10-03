package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

public class _BaseController {
	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("historyBack", true);
		req.setAttribute("msg", msg);
		
		return "common/redirect";
	}
	
	protected String msgAndReplace(HttpServletRequest req, String msg, String redirectUri) {
		req.setAttribute("redirectUri", redirectUri);
		req.setAttribute("msg", msg);
		
		return "common/redirect";
	}
}

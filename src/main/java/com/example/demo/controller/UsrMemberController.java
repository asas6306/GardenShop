package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrMemberController {
	@Autowired
	MemberService ms;
	
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession session, String redirectUri, String ID, String PW) {
		
		ResultData doLoginRd = ms.login(ID, PW);

		if (doLoginRd.isFail())
			return Util.msgAndBack(doLoginRd.getMsg());

		// BeforeActionInterceptor를 위한 세션
		Member member = (Member) doLoginRd.getBody().get("loginedMember");
		
		session.setAttribute("loginedMemberUid", member.getUid());
		session.setAttribute("loginedMemberJsonStr", member.toJsonStr());
		
		// 임시 비밀번호 관련 로직
		boolean isTempPassword = ms.isTempPassword(member.getUid());
		boolean needToChangePassword = ms.needToChangePassword(member.getUid());
		
        String msg = null;
        if ( isTempPassword ) {
            msg = "임시 비밀번호를 변경해주세요.";
            redirectUri = "/usr/member/mypage";
        } else if(needToChangePassword) { 
        	msg = "비밀번호를 변경한지 " + ms.getNeedToChangePassword() + "일이 경과하였습니다. 비밀번호를 변경해주세요.";
            redirectUri = "/usr/member/mypage";
		} else {
        	msg = String.format("%s님 환영합니다.", member.getNickname());
        	redirectUri = Util.ifEmpty(redirectUri, "../home/main");        	
        }

		return Util.msgAndReplace(msg, redirectUri);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session, HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		System.out.println("test!" + uri);
		
		session.removeAttribute("loginedMemberUid");
		session.removeAttribute("loginedMemberJsonStr");
		
		return Util.msgAndReplace("로그아웃 되었습니다.", null);
	}
}

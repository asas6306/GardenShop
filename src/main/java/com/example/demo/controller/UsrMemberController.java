package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.example.demo.dto.Member;
import com.example.demo.dto.Rq;
import com.example.demo.service.GenFileService;
import com.example.demo.service.MemberService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrMemberController extends _BaseController {
	@Autowired
	MemberService ms;
	@Autowired
	GenFileService fs;
	
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession session, String afterLoginUri, String ID, String PW) {
		
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
            afterLoginUri = "/usr/member/mypage";
        } else if(needToChangePassword) { 
        	msg = "비밀번호를 변경한지 " + ms.getNeedToChangePassword() + "일이 경과하였습니다. 비밀번호를 변경해주세요.";
            afterLoginUri = "/usr/member/mypage";
		} else {
        	msg = String.format("%s님 환영합니다.", member.getNickname());
        	afterLoginUri = Util.ifEmpty(afterLoginUri, "../home/main");        	
        }

		return Util.msgAndReplace(msg, "../.." + afterLoginUri);
	}
	
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public String logout(String afterLogoutUri) {
		
		return Util.msgAndReplace(null, "doLogout?afterLogoutUri=" + afterLogoutUri);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session, String afterLogoutUri) {
		
		session.removeAttribute("loginedMemberUid");
		session.removeAttribute("loginedMemberJsonStr");
		
		return Util.msgAndReplace("로그아웃 되었습니다.", afterLogoutUri);
	}
	
	@RequestMapping("/usr/member/signup")
	public String signup() {
		
		return "usr/member/signup";
	}
	
	@RequestMapping("/usr/member/doSignup")
	public String doSignup(HttpServletRequest req, @RequestParam Map<String, Object> param, 
			MultipartRequest multipartRequest) {

		ResultData doSignupRd = ms.signup(param);

		int uid = (int)doSignupRd.getBody().get("uid");

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        for (String fileInputName : fileMap.keySet()) {
            MultipartFile multipartFile = fileMap.get(fileInputName);

            if ( multipartFile.isEmpty() == false ) {
                fs.save(multipartFile, uid);
            }
        }
		
		String redirectUri = Util.ifEmpty((String) param.get("afterUri"), "../home/main");
		System.out.println("test!" + param.get("afterUri"));
		
		if(doSignupRd.isSuccess())
			return msgAndReplace(req, doSignupRd.getMsg(), redirectUri);
		else
			return msgAndBack(req, doSignupRd.getMsg());
	}
	
	// 아이디 체크
	@GetMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String ID) {
		if (ID == null)
			return new ResultData("F-1", "ID를 입력해주세요.");
		if (ID.length() < 5)
			return new ResultData("F-6", "아이디를 5자 이상으로 입력하세요.");
		if (ID.length() > 15)
			return new ResultData("F-6", "아이디를 15자 이하로 입력하세요.");
		if (Util.isStandardLoginIdCheck(ID) == false)
			return new ResultData("F-5", "아이디는 영문과 숫자의 조합으로 구성되어야 합니다.");

		Member member = ms.getMember("ID", ID);

		if (member != null)
			return new ResultData("F-2", String.format("%s(은)는 이미 사용중인 아이디입니다.", ID));

		return new ResultData("S-1", String.format("%s(은)는 사용 가능한 아이디입니다.", ID), "ID", ID);
	}
	
	// 닉네임 체크
	@GetMapping("/usr/member/getNicknameDup")
	@ResponseBody
	public ResultData getNicknameDup(String nickname) {
		if (nickname == null)
			return new ResultData("F-1", "닉네임을 입력해주세요.");
		if (nickname.length() < 5)
			return new ResultData("F-6", "닉네임을 5자 이상으로 입력하세요.");
		if (nickname.length() > 15)
			return new ResultData("F-6", "닉네임을 15자 이하로 입력하세요.");

		Member member = ms.getMember("nickname", nickname);

		if (member != null)
			return new ResultData("F-2", String.format("%s(은)는 이미 사용중인 닉네임입니다.", nickname));

		return new ResultData("S-1", String.format("%s(은)는 사용 가능한 닉네임입니다.", nickname), "nickname", nickname);
	}

	// 비밀번호 체크
	@GetMapping("/usr/member/getPWDup")
	@ResponseBody
	public ResultData getPWDup(String PW) {
		if (PW == null)
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		if (PW.length() < 8)
			return new ResultData("F-6", "비밀번호를 8자 이상으로 입력하세요.");
		if (PW.length() > 15)
			return new ResultData("F-6", "비밀번호를 15자 이하로 입력하세요.");
		if (Util.allNumberString(PW))
			return new ResultData("F-3", "비밀번호는 숫자로만 구성될 수 없습니다.");
		if (Util.isStandardLoginIdCheck(PW) == false)
			return new ResultData("F-5", "비밀번호 형식을 확인해주세요.");

		return new ResultData("S-1", "", "PW", PW);
	}
	
	// 연락처 체크
	@GetMapping("/usr/member/getPhoneNoDup")
	@ResponseBody
	public ResultData getPhoneNoDup(String phoneNo) {
		if (phoneNo == null)
			return new ResultData("F-1", "연락처를 입력해주세요.");
		String[] splitPhoneNo = phoneNo.split("-");
		if(splitPhoneNo.length != 3)
			return new ResultData("F-4", "전화번호 형식을 확인해주세요.");
		if (splitPhoneNo[0].length() != 3 && splitPhoneNo[1].length() != 4 && splitPhoneNo[2].length() != 4)
			return new ResultData("F-3", "올바른 전화번호 형식이 아닙니다.");
		if (Util.allNumberString(splitPhoneNo[0]) && Util.allNumberString(splitPhoneNo[1]) && Util.allNumberString(splitPhoneNo[2]))
			return new ResultData("S-1", "", "phoneNo", phoneNo);

		return new ResultData("F-2", "올바른 전화번호 형식이 아닙니다.");
	}
	
	// 이메일 체크
	@GetMapping("/usr/member/getEmailDup")
	@ResponseBody
	public ResultData getEmailDup(String email) {
		if (email == null)
			return new ResultData("F-1", "이메일을 입력해주세요.");
		else if(email.contains("@")) {
			if(ms.getMember("email", email) != null)
				return new ResultData("F-3", "이미 등록된 이메일입니다.");
			
			return new ResultData("S-1", "", "email", email);
		} else {
			return new ResultData("F-4", "이메일 형식을 확인해주세요.");
		}
	}
	
	// 마이페이지 이동
	@RequestMapping("/usr/member/mypage")
	public String mypage(HttpServletRequest req, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "article") String call, @RequestParam Map<String, Object> param) {


		return "usr/member/mypage";
	}
	
	// 아이디 찾기 페이지로 이동
	@RequestMapping("/usr/member/findID")
	public String findID() {

		return "usr/member/findID";
	}
	
	// 아이디 찾기 동작
	@RequestMapping("/usr/member/doFindID")
	@ResponseBody
	public String doFindID(String name, String email) {
		Member member = ms.getMemberForFindId(name, email);
		
		if (member == null)
			return Util.msgAndBack("일치하는 회원을 찾을 수 없습니다.");
		
		String msg = "회원님의 아이디는 " + member.getID() + " 입니다.";
		String redirectUri = Util.ifEmpty(null, "findPW");
		return Util.msgAndReplace(msg, redirectUri);
	}
	
	// 비밀번호 찾기 페이지로 이동
	@RequestMapping("/usr/member/findPW")
	public String findPW() {

		return "usr/member/findPW";
	}
	
	// 비밀번호 찾기 동작
	@RequestMapping("/usr/member/doFindPW")
	@ResponseBody
	public String doFindPW(String ID, String email) {
		Member member = ms.getMember("ID", ID);
		
		if (member == null) {
	        return Util.msgAndBack("일치하는 회원이 존재하지 않습니다.");
	    }

	    if (!member.getEmail().equals(email)) {
	        return Util.msgAndBack("일치하는 회원이 존재하지 않습니다.");
	    }

	    ResultData notifyTempLoginPwByEmailRs = ms.notifyTempLoginPwByEmail(member);

	    return Util.msgAndReplace(notifyTempLoginPwByEmailRs.getMsg(), "login");
	}
	
	// 인증페이지로 이동
	@RequestMapping("/usr/member/authentication")
	public String authentication(HttpServletRequest req) {
		
		return "usr/member/authentication";
	}
	
	// 인증페이지 동작
	@RequestMapping("/usr/member/doAuthentication")
	@ResponseBody
	public String doAuthentication(HttpServletRequest req, String PW, String redirectUri) {
		Rq rq = (Rq)req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		Member loginedMember = ms.getMember("uid", String.valueOf(uid));
		String orignalPW = loginedMember.getPW();
		
		if (!PW.equals(orignalPW)) {
	        return Util.msgAndBack("비밀번호가 일치하지 않습니다.");
	    }
		
		String authCode = ms.genCheckPasswordAuthCode(uid);
		
		redirectUri = Util.getNewUri(redirectUri, "checkPasswordAuthCode", authCode);

		return Util.msgAndReplace(null, redirectUri);
	}
}

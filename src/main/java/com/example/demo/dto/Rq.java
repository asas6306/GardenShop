package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Map;

import com.example.demo.util.Util;

import lombok.Getter;

public class Rq {
    private String currentUrl;
    private String currentUri;
    private Member loginedMember;
    private Map<String, String> paramMap;
    @Getter
    private boolean needToChangePassword;
    @Getter
    private boolean isAjax;
    @Getter
    private boolean isAdmin;

    public Rq(boolean isAjax, Member loginedMember, String currentUri, Map<String, String> paramMap, boolean needToChangePassword) {
        this.loginedMember = loginedMember;
        this.currentUrl = currentUri.split("\\?")[0];
        this.currentUri = currentUri;
        this.paramMap = paramMap;
        this.needToChangePassword = needToChangePassword;
    }
    
    public String getParamJsonStr() {
        return Util.toJsonStr(paramMap);
    }

    public boolean isLogined() {
        return loginedMember != null;
    }

    public boolean isNotLogined() {
        return isLogined() == false;
    }
    
    public boolean isAdmin() {
        return loginedMember.getAuthLevel() == 7;
    }

    public int getLoginedMemberUid() {
        if (isNotLogined()) return 0;

        return loginedMember.getUid();
    }

    public Member getLoginedMember() {
        return loginedMember;
    }
    
    public String getEncodedCurrentUri(String uri) {
        return Util.getUriEncoded(uri);
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public String getLoginPageUri() {
        String afterLoginUri;
        
        if (isLoginPage()) {
            afterLoginUri = paramMap.get("afterLoginUri");
        } else if(isNeedLogoutPage()){
        	afterLoginUri = "/usr/home/main";
        } else {
            afterLoginUri = getCurrentUri();
        }

        return afterLoginUri;
    }

    private boolean isLoginPage() {
        return currentUrl.equals("/usr/member/login");
    }
    
    private boolean isNeedLogoutPage() {
    	ArrayList<String> needLogoutPage = new ArrayList<String>(); 
    	needLogoutPage.add("/usr/member/signup");
    	needLogoutPage.add("/usr/member/findID");
    	needLogoutPage.add("/usr/member/findPW");
    	
    	return needLogoutPage.contains(currentUrl);
    }
    
    public String getLogoutPageUri() {
        String afterLogoutUri;
        
        if (isLoginPage()) {
        	afterLogoutUri = Util.getUriEncoded(paramMap.get("afterLogoutUri"));
        } else {
        	afterLogoutUri = getEncodedCurrentUri(getCurrentUri());
        }

        return "../member/logout?afterLogoutUri=" + afterLogoutUri;
    }
    
    private boolean isSignupPage() {
        return currentUrl.equals("/usr/member/signup");
    }
    
    public String getAfterUri() {
    	String afterSignupUri;
    	
    	if (isSignupPage()) {
    		afterSignupUri = Util.getUriEncoded(paramMap.get("afterUri"));
        } else {
        	afterSignupUri = getEncodedCurrentUri(getCurrentUri());
        }
    	
    	return afterSignupUri;
    }
    
    
}
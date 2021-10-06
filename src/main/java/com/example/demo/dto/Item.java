package com.example.demo.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	private int iid;
	private String name;
	private String group;
	private String price;
	private String regDate;
	
	private int count;		// basket 연동 수량
	
	private String extra__thumbImg;
	private Map<String, Object> extra;
	
	public Map<String, Object> getExtraNotNull() {
		if(extra == null) 
			extra = new HashMap<String, Object>();
		
		return extra;
	}
	
	
	public String getItemImgUri(String type) {
        return "/common/genFile/file/car/" + iid + "/common/" + type + "/0";
    }
	
	public String getProfileFallbackImgUri() {
        return "/gen/member/basic/non_profile.png";
    }

    public String getProfileFallbackImgOnErrorHtmlAttr() {
        return "this.src = '" + getProfileFallbackImgUri() + "'";
    }
}

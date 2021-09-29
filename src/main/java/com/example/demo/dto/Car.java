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
public class Car {
	private int cid;
	private String name;
	private int year;
	private int price;
	
	private String extra__thumbImg;
	private Map<String, Object> extra;
	
	public Map<String, Object> getExtraNotNull() {
		if(extra == null) 
			extra = new HashMap<String, Object>();
		
		return extra;
	}
	
	
	public String getMainCarImgUri(String type) {
        return "/common/genFile/file/car/" + cid + "/common/" + type + "/0";
    }
	
	public String getWriterProfileFallbackImgUri() {
        return "/gen/member/basic/non_profile.png";
    }

    public String getWriterProfileFallbackImgOnErrorHtmlAttr() {
        return "this.src = '" + getWriterProfileFallbackImgUri() + "'";
    }
}

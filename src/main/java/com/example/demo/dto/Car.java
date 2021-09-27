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
}

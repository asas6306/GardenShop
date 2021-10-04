package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CounselDao {

	public void reqCounsel(@Param(value="target") String target, @Param(value="year") String year, @Param(value="uid") int uid);

}

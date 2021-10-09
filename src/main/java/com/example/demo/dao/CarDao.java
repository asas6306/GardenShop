package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Car;

@Mapper
public interface CarDao {

	public List<Car> getBestCar();

	public List<Car> getCars(@Param(value="group") String group);

	public List<Car> getRecommend();

	public List<Car> getCarsWithPaging(@Param(value="group") String group, @Param(value="page") int page, @Param(value="pageCnt") int pageCnt);

	public int getCarsCnt(@Param(value="group") String group);

}

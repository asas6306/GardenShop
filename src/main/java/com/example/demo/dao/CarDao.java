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

}

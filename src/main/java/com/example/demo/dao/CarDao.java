package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Car;

@Mapper
public interface CarDao {

	public List<Car> getBestCar();

}

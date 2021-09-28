package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CarDao;
import com.example.demo.dto.Car;

@Service
public class CarService {
	@Autowired
	CarDao cd;
	
	public List<Car> getBest() {
		
		return cd.getBestCar();
	}
	
}

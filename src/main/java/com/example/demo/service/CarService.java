package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CarDao;
import com.example.demo.dto.Car;
import com.example.demo.dto.GenFile;

@Service
public class CarService {
	@Autowired
	CarDao cd;
	@Autowired
	GenFileService fs;
	
	public List<Car> getBest() {
		List<Car> cars = cd.getBestCar();
		
		// 해당 이미지 가져오기
		List<Integer> cids = cars.stream().map(car -> car.getCid())
				.collect(Collectors.toList());
		if(!cids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("car", cids, "common", "all");

			for(Car car : cars) {
				Map<String, GenFile> mapByFileNo = filesMap.get(car.getCid());

				if (mapByFileNo != null)
					car.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return cars;
	}

	public List<Car> getCars(String group, String sort) {
		
		List<Car> cars = cd.getCars(group, sort);
		
		// 해당 이미지 가져오기
		List<Integer> cids = cars.stream().map(car -> car.getCid())
				.collect(Collectors.toList());
		if(!cids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("car", cids, "common", group);

			for(Car car : cars) {
				Map<String, GenFile> mapByFileNo = filesMap.get(car.getCid());

				if (mapByFileNo != null)
					car.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return cars;
	}
	
	public List<Car> getCarsWithPaging(String group, int page, int pageCnt) {
		
		List<Car> cars = cd.getCarsWithPaging(group, page,pageCnt);
		
		// 해당 이미지 가져오기
		List<Integer> cids = cars.stream().map(car -> car.getCid())
				.collect(Collectors.toList());
		if(!cids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("car", cids, "common", group);

			for(Car car : cars) {
				Map<String, GenFile> mapByFileNo = filesMap.get(car.getCid());

				if (mapByFileNo != null)
					car.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return cars;
	}
	
	public List<Car> getRecommend() {
		
		List<Car> cars = cd.getRecommend();
		
		// 해당 이미지 가져오기
		List<Integer> cids = cars.stream().map(car -> car.getCid())
				.collect(Collectors.toList());
		if(!cids.isEmpty()) {
			Map<Integer, Map<String, GenFile>> filesMap = 
					fs.getFilesMapKeyRelIdAndFileNo("car", cids, "common", "all");

			for(Car car : cars) {
				Map<String, GenFile> mapByFileNo = filesMap.get(car.getCid());

				if (mapByFileNo != null)
					car.getExtraNotNull().put("file__common__all", mapByFileNo);
			}
		}
		
		return cars;
	}

	public int getCarsCnt(String group) {
		
		return cd.getCarsCnt(group);
	}

	
}

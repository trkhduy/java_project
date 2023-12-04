package com.example.project_spring.dao;

import java.util.List;

import com.example.project_spring.entity.Image;

public interface ImageDAO extends GenericInterface<Image, Integer> {
	List<Image> getImageByRoom(String roomId);
	void bulkInsert(List<Image> images);
	void deleteByRoom(String roomId);
}

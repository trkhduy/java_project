package com.example.project_spring.dao;

import java.util.List;

import com.example.project_spring.entity.RoomType;

public interface RoomTypeDAO extends GenericInterface<RoomType, Integer> {
	List<RoomType> search(String typeName);
	List<RoomType> checkTypeName(String typeName);
	List<RoomType> paginate(Integer pageNo, Integer pageSize);
	int count();
	List<RoomType> searchPage(String keyWord, Integer pageNo, Integer pageSize);
	int countPage(String keyWord);
}

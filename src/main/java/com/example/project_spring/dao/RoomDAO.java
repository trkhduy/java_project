package com.example.project_spring.dao;

import java.util.List;
import com.example.project_spring.entity.Room;

public interface RoomDAO extends GenericInterface<Room, String> {
	List<Room> search(String name);

	List<Room> checkName(String name);

	List<Room> paginate(Integer pageNo, Integer pageSize);

	int count();

	List<Room> searchPage(String keyWord, Integer pageNo, Integer pageSize);

	int countPage(String keyWord);
	
	List<Room> getRoomByType(Integer roomTypeId);
}

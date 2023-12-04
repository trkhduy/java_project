package com.example.project_spring.dao;

import java.util.List;

import com.example.project_spring.entity.RoomFacility;

public interface RoomFacilityDAO extends GenericInterface<RoomFacility, Integer> {
	void bulkInsert(List<RoomFacility> roomFacilities);
	void deleteByRoom(String roomId);
	List<RoomFacility> getByFacility(Integer facilityId);
	List<RoomFacility> getByRoom(String roomId);
}

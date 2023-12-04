package com.example.project_spring.dao;

import java.util.List;

import com.example.project_spring.entity.RoomPolicy;

public interface RoomPolicyDAO extends GenericInterface<RoomPolicy, Integer> {
	void bulkInsert(List<RoomPolicy> roomPolicies);
	void deleteByRoom(String roomId);
	List<RoomPolicy> getByPolicy(Integer policyId);
	List<RoomPolicy> getByRoom(String roomId);
}

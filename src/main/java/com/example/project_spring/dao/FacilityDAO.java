package com.example.project_spring.dao;

import java.util.List;

import com.example.project_spring.entity.Facility;

public interface FacilityDAO extends GenericInterface<Facility, Integer> {
	List<Facility> search(String name);

	List<Facility> checkName(String name);

	List<Facility> paginate(Integer pageNo, Integer pageSize);

	int count();

	List<Facility> searchPage(String keyWord, Integer pageNo, Integer pageSize);

	int countPage(String keyWord);
}
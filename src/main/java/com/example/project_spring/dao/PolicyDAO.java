package com.example.project_spring.dao;

import java.util.List;
import com.example.project_spring.entity.Policy;

public interface PolicyDAO extends GenericInterface<Policy, Integer> {
	List<Policy> search(String name);

	List<Policy> checkName(String name);

	List<Policy> paginate(Integer pageNo, Integer pageSize);

	int count();

	List<Policy> searchPage(String keyWord, Integer pageNo, Integer pageSize);

	int countPage(String keyWord);
}

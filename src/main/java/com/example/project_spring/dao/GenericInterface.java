package com.example.project_spring.dao;

import java.util.List;

public interface GenericInterface<T, V> {
	
	public List<T> getAll();

	public T find(V id);

	public void insert(T obj);

	public void update(T obj);

	public void delete(V id);
}

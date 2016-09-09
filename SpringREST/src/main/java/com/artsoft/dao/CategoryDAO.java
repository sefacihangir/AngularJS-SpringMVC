package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Category;

public interface CategoryDAO {
	
	Category findById(int id);
	
	Category findByName(String name);
	
	List<Category> findAll();
	
	int insert(Category category);
	
	void update(Category category);
	
}

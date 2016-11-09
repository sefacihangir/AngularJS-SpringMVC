package com.artsoft.service;

import java.util.List;

import com.artsoft.model.Category;

public interface CategoryService {
	
	Category findById(int id);
	
	Category findByName(String name);
	
	List<Category> findAll();
	
	int insert(Category category);
	
	void update(Category category);
	
	boolean isCategoryNameAvailable(Category category);
	
	void delete(Category category);
	
}

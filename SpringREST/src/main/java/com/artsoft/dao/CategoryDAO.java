package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Category;

public interface CategoryDAO {
	
	Category findById(int id);
	
	Category findByName(String name);
	
	boolean findCategoryNameAvailability(Category category);
	
	List<Category> findAll();
	
	int insert(Category category);
	
	void update(Category category);
	
	void delete(Category category);
	
	boolean findCategoryAvailability(Category category);
	
}

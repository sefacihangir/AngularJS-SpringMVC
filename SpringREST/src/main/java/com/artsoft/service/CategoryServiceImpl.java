package com.artsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.CategoryDAO;
import com.artsoft.model.Category;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDao;
	
	
	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public Category findByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public int insert(Category category) {
		return categoryDao.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public boolean isCategoryNameAvailable(Category category) {
		return categoryDao.findCategoryNameAvailability(category);
	}

	@Override
	public void delete(Category category) {
		categoryDao.delete(category);
	}

}

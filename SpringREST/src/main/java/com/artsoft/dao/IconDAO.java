package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Icon;

public interface IconDAO {
	
	Icon findById(int id);
	
	List<Icon> findAll();
	
	int insert(Icon icon);
	
	void update(Icon icon);
	
}

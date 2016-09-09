package com.artsoft.dao;

import com.artsoft.model.Service;

public interface ServiceDAO {
	
	Service findById(int id);
	
	Service findByName(String name);
	
	int insert(Service service);
	
	void update(Service service);
	
}

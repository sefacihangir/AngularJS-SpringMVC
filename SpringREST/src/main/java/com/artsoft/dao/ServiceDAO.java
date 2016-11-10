package com.artsoft.dao;


import com.artsoft.model.ServiceModel;

public interface ServiceDAO {
	
	ServiceModel findById(int id);
	
	ServiceModel findByName(String name);
	
	int insert(ServiceModel service);
	
	void update(ServiceModel service);
	
	void delete(ServiceModel service);
	
	boolean findServiceNameAvailability(ServiceModel service);
	
}

package com.artsoft.service;

import com.artsoft.model.ServiceModel;

public interface ServiceModelService {

	ServiceModel findById(int id);
	
	ServiceModel findByName(String name);
	
	int insert(ServiceModel service);
	
	void update(ServiceModel service);
	
	void delete(ServiceModel service);
	
	boolean isServiceNameAvailable(ServiceModel service);
	
}

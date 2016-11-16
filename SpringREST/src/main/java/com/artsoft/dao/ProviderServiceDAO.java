package com.artsoft.dao;

import com.artsoft.model.ProviderService;

public interface ProviderServiceDAO {
	
	int insert(ProviderService providerService);
	
	void update(ProviderService providerService);
	
	boolean findProviderServiceAvailability(ProviderService providerService);
	
	void delete(ProviderService providerService);
	
	ProviderService findById(int id);
	
}

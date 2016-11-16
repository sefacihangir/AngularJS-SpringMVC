package com.artsoft.service;

import com.artsoft.model.ProviderService;

public interface ProviderServiceService {

	int insert(ProviderService providerService);
	
	void update(ProviderService providerService);
	
	boolean isProviderServiceAsserted(ProviderService providerService);
	
}

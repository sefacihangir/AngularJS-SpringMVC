package com.artsoft.service;

import com.artsoft.model.ProviderCategoryList;

public interface ProviderCategoryListService {
	
	ProviderCategoryList findByProviderId(int id);
	
	int insert(ProviderCategoryList providerCategoryList);
	
	void update(ProviderCategoryList providerCategoryList);
	
	boolean isListNameAvailable(ProviderCategoryList providerCategoryList);
	
	ProviderCategoryList findByProviderCategoryListId(int id);
	
}

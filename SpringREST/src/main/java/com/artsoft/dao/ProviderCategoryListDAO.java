package com.artsoft.dao;

import com.artsoft.model.ProviderCategoryList;

public interface ProviderCategoryListDAO {
	
	ProviderCategoryList findByProviderId(int id);
	
	int insert(ProviderCategoryList providerCategoryList);
	
	void update(ProviderCategoryList providerCategoryList);
	
}

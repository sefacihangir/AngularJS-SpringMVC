package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.ProviderCategoryList;

public interface ProviderCategoryListDAO {
	
	ProviderCategoryList findByProviderId(int id);
	
	int insert(ProviderCategoryList providerCategoryList);
	
	void update(ProviderCategoryList providerCategoryList);
	
	boolean findListNameAvailability(ProviderCategoryList providerCategoryList);
	
	ProviderCategoryList findByProviderCategoryListId(int id);
	
	List<ProviderCategoryList> findAll();
	
	List<ProviderCategoryList> findAllForProvider(ProviderCategoryList providerCategoryList);
	
}

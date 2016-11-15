package com.artsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.ProviderCategoryListDAO;
import com.artsoft.model.ProviderCategoryList;

@Service("providerCategoryListService")
@Transactional
public class ProviderCategoryListServiceImpl implements ProviderCategoryListService{

	@Autowired
	ProviderCategoryListDAO providerCategoryListDao;
	
	@Override
	public ProviderCategoryList findByProviderId(int id) {
		return providerCategoryListDao.findByProviderId(id);
	}

	@Override
	public int insert(ProviderCategoryList providerCategoryList) {
		return providerCategoryListDao.insert(providerCategoryList);
	}

	@Override
	public void update(ProviderCategoryList providerCategoryList) {
		providerCategoryListDao.update(providerCategoryList);
	}

	@Override
	public boolean isListNameAvailable(ProviderCategoryList providerCategoryList) {
		return providerCategoryListDao.findListNameAvailability(providerCategoryList);
	}

	@Override
	public ProviderCategoryList findByProviderCategoryListId(int id) {
		return providerCategoryListDao.findByProviderCategoryListId(id);
	}

}

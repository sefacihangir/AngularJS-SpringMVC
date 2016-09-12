package com.artsoft.dao;

import com.artsoft.model.ProviderService;

public class ProviderServiceDAOImpl extends AbstractDao implements ProviderServiceDAO{

	@Override
	public int insert(ProviderService providerService) {
		int insertedProviderServiceId = (int) getSession().save(providerService);
		return insertedProviderServiceId;
	}

	@Override
	public void update(ProviderService providerService) {
		getSession().update(providerService);
	}

}

package com.artsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.ProviderServiceDAO;
import com.artsoft.model.ProviderService;

@Service("providerServiceImpl")
@Transactional
public class ProviderServiceServiceImpl implements ProviderServiceService{
	
	@Autowired
	ProviderServiceDAO providerServiceDao;

	@Override
	public int insert(ProviderService providerService) {
		return providerServiceDao.insert(providerService);
	}

	@Override
	public void update(ProviderService providerService) {
		providerServiceDao.update(providerService);
	}

	@Override
	public boolean isProviderServiceAsserted(ProviderService providerService) {
		return providerServiceDao.findProviderServiceAvailability(providerService);
	}

	@Override
	public void delete(ProviderService providerService) {
		providerServiceDao.delete(providerService);
	}

	@Override
	public ProviderService findById(int id) {
		return providerServiceDao.findById(id);
	}
	
}

package com.artsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.ServiceDAO;
import com.artsoft.model.ServiceModel;

@Service("serviceModelService")
@Transactional
public class ServiceModelServiceImpl implements ServiceModelService{

	
	@Autowired
	ServiceDAO serviceDao;
	
	@Override
	public ServiceModel findById(int id) {
		return serviceDao.findById(id);
	}

	@Override
	public ServiceModel findByName(String name) {
		return serviceDao.findByName(name);
	}

	@Override
	public int insert(ServiceModel service) {
		return serviceDao.insert(service);
	}

	@Override
	public void update(ServiceModel service) {
		serviceDao.update(service);
	}

	@Override
	public void delete(ServiceModel service) {
		serviceDao.delete(service);
	}

}

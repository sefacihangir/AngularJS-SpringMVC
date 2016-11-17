package com.artsoft.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.model.AppUser;
import com.artsoft.model.Request;
import com.artsoft.service.RequestService;

@Service("requestService")
@Transactional
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDAO requestDao;

	@Autowired
	AppUserDAO appUserDao;

	@Override
	public int insert(Request request) {
		return requestDao.insert(request);
	}

	@Override
	public void update(Request request) {
		requestDao.update(request);
	}

	@Override
	public List<Request> findAllFor(int id, String role) {
		AppUser user = appUserDao.findById(id);
		if (user != null) {
			switch (role.toUpperCase()) {
			case "ROLE_PROVIDER":
				return requestDao.findAllForProvider(user);
			case "ROLE_CUSTOMER":
				return requestDao.findAllForCustomer(user);
			default:
				break;
			}
		}
		return null;
	}

}

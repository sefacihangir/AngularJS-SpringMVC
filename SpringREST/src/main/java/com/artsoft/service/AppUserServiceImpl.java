package com.artsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.AppUserDAO;
import com.artsoft.model.AppUser;


@Service("appUserService")
@Transactional
public class AppUserServiceImpl implements AppUserService{
	
	
	@Autowired
	AppUserDAO appUserDao;
	
	@Override
	public AppUser findByEmail(String email) {
		System.out.println("IN SERVICE");
		return appUserDao.findByEmail(email);
	}

}

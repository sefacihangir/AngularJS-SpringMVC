package com.artsoft.service;

import java.util.List;

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
		return appUserDao.findByEmail(email);
	}

	@Override
	public AppUser findById(int id) {
		return appUserDao.findById(id);
	}

	@Override
	public List<AppUser> findAll() {
		return appUserDao.findAll();
	}

	@Override
	public int insert(AppUser user) {
		return appUserDao.insert(user);
	}

	@Override
	public void update(AppUser user) {
		appUserDao.update(user);
	}

	@Override
	public boolean isEmailAvailable(String email) {
		return appUserDao.findEmailAvailability(email);
	}

	@Override
	public void delete(AppUser user) {
		appUserDao.delete(user);
	}

}

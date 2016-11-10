package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.AppUser;


public interface AppUserDAO {
	
	AppUser findByEmail(String email);
	
	boolean findEmailAvailability(String email);
	
	AppUser findById(int id);
	
	List<AppUser> findAll();
	
	int insert(AppUser user);
	
	void update(AppUser user);
	
}

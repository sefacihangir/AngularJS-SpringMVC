package com.artsoft.service;

import java.util.List;

import com.artsoft.model.AppUser;

public interface AppUserService {
	
	AppUser findByEmail(String email);

	AppUser findById(int id);
	
	List<AppUser> findAll();
	
	int insert(AppUser user);
	
	void update(AppUser user);
	
	boolean isEmailAvailable(String email);
	
}

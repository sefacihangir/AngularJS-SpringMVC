package com.artsoft.dao;

import com.artsoft.model.AppUser;


public interface AppUserDAO {
	
	AppUser findByEmail(String email);
	
}

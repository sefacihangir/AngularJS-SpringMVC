package com.artsoft.service;

import com.artsoft.model.AppUser;

public interface AppUserService {
	
	AppUser findByEmail(String email);
	
}

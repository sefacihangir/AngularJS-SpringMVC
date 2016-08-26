package com.artsoft.service;

import java.util.List;

import com.artsoft.model.User;

public interface ServiceUser {
	
	public List<User> getAllUsers();
	public User getUserById(long userId);
	
	
}

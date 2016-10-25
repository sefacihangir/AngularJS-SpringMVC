package com.artsoft.service;

import java.util.List;

import com.artsoft.model.Role;

public interface RoleService {
	
	Role findById(int id);
	
	List<Role> findAll();
	
	int insert(Role role);
	
	void update(Role role);
	
}

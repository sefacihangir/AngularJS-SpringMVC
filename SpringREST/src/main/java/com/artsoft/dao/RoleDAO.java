package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Role;

public interface RoleDAO {
	
	Role findById(int id);
	
	Role findByName(String name);
	
	List<Role> findAll();
	
	int insert(Role role);
	
	void update(Role role);
	
}

package com.artsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.RoleDAO;
import com.artsoft.model.Role;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDAO roleDao;
	
	
	@Override
	public Role findById(int id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public int insert(Role role) {
		return roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

}

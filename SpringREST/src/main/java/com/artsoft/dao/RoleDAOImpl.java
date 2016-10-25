package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.Role;

@Repository("roleDao")
public class RoleDAOImpl extends AbstractDao implements RoleDAO{

	@Override
	public Role findById(int id) {
		String sql = "SELECT r FROM Role r WHERE r.roleId = :id";
		Query query = getSession().createQuery(sql).setParameter("roleId", id);
		return (Role) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		return getSession().createQuery("FROM Role").list();
	}
	
	@Override
	public int insert(Role role) {
		int insertedRoleId = (int) getSession().save(role);
		return insertedRoleId;
	}

	@Override
	public void update(Role role) {
		getSession().update(role);
	}



}

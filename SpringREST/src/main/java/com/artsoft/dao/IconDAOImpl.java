package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.Icon;

@Repository("iconDao")
public class IconDAOImpl extends AbstractDao implements IconDAO{

	@Override
	public Icon findById(int id) {
		String sql = "SELECT i FROM Icon i WHERE i.iconId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (Icon) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Icon> findAll() {
		return getSession().createQuery("FROM Icon").list();
	}

	@Override
	public int insert(Icon icon) {
		int insertedIconId = (int) getSession().save(icon);
		return insertedIconId;
	}

	@Override
	public void update(Icon icon) {
		getSession().update(icon);
	}

}

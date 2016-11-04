package com.artsoft.dao;




import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.artsoft.model.AppUser;


@Repository("appUserDao")
public class AppUserDAOImpl extends AbstractDao implements AppUserDAO{

	@Override
	public AppUser findByEmail(String email) {
		String sql = "SELECT u FROM AppUser u WHERE u.email = :email";
		Query query = getSession().createQuery(sql).setParameter("email", email);
		return (AppUser) query.uniqueResult();
	}

	@Override
	public AppUser findById(int id) {
		String sql = "SELECT u FROM AppUser u WHERE u.appUserId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (AppUser) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppUser> findAll() {
		return getSession().createQuery("FROM AppUser").list();
	}

	@Override
	public int insert(AppUser user) {
		int insertedUserId = (int) getSession().save(user);
		return insertedUserId;
	}

	@Override
	public void update(AppUser user) {
		getSession().update(user);
	}

	@Override
	public boolean findEmailExistence(String email) {
		Criteria criteria = getSession().createCriteria(AppUser.class);
		criteria.add(Restrictions.ne("email", email));
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		System.out.println("Email count-- " + count);
		return count != 0 ?  true : false;
	}


	
	

}

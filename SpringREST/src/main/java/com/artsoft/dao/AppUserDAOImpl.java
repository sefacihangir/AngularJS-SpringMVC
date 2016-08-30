package com.artsoft.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.artsoft.model.AppUser;


@Repository("appUserDao")
public class AppUserDAOImpl extends AbstractDao implements AppUserDAO{

	@Override
	public AppUser findByEmail(String email) {
		System.out.println("IN DAO");
		String sql = "SELECT u FROM AppUser u WHERE u.email = :email";
		Query query = getSession().createQuery(sql)
								  .setParameter("email", email);
		return (AppUser) query.uniqueResult();
	}

}

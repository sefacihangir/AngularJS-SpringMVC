package com.artsoft.dao;

import org.springframework.stereotype.Repository;


import com.artsoft.model.AppUser;


@Repository("appUserDao")
public class AppUserDAOImpl extends AbstractDao implements AppUserDAO{

	@Override
	public AppUser findByEmail(String email) {
//		return (AppUser) getSession()
//				.createQuery("SELECT u FROM AppUser u WHERE u.email = :email")
//				.setParameter("email", email)
//				.uniqueResult();
		AppUser user = new AppUser();
		user.setAppUserId(1);
		user.setEmail("berar1994@gmail.com");
		user.setPassword("1234");
		return user;
	}

}

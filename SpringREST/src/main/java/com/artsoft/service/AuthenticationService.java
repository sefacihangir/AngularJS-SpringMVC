package com.artsoft.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artsoft.dao.AppUserDAO;
import com.artsoft.model.AppUser;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService{

	@Autowired
	AppUserDAO appUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = appUserDao.findByEmail(email);
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
		UserDetails userDetails = (UserDetails)new User(user.getEmail(), user.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}

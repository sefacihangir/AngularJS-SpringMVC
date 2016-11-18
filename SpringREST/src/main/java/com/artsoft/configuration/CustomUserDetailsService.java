package com.artsoft.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artsoft.model.AppUser;
import com.artsoft.service.AppUserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	AppUserService appUserService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = appUserService.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Email not found.");
		}
		
		System.out.println("USER-- " + user.getEmail());
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
                user.getAccount().getState().getDescription().equals("Active".toUpperCase()), true, true, true, getGrantedAuthorities(user));
	}
	
	
	
	private List<GrantedAuthority> getGrantedAuthorities(AppUser user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return authorities;
    }

}

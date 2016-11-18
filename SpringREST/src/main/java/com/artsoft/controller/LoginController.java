package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.AppUser;
import com.artsoft.model.Role;
import com.artsoft.service.AppUserService;

@RestController
@RequestMapping("/login_control")
@EnableWebMvc
public class LoginController {

	@Autowired
	AppUserService appUserService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetailsService customUserDetailsService;

	@RequestMapping(value = "/login", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

		// clear the security context
		SecurityContextHolder.clearContext();
		
		Map<String, Object> response = new HashMap<String, Object>();
		UserDetails userDetails = null;
		
		try{
			userDetails = customUserDetailsService.loadUserByUsername(email);
		}catch(UsernameNotFoundException ex){
			 CustomError error = new CustomError();
			 error.setHasError(true);
			 error.setErrorOnField("email");
			 error.setErrorMessage("Incorrect email.");
			 response.put("error", error);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
		}

		
		if ( (userDetails != null) && passwordEncoder.matches(password, userDetails.getPassword()) ) {
			Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			AppUser user = new AppUser();
			user.setEmail(userDetails.getUsername());
			
			for(GrantedAuthority r : userDetails.getAuthorities()){
				Role role = new Role();
				role.setRoleName(r.getAuthority());
				user.setRole(role);
			}
			
			
			response.put("user", user);
		}
		else{
			 CustomError error = new CustomError();
			 error.setHasError(true);
			 error.setErrorOnField("password");
			 error.setErrorMessage("Incorrect password.");
			 response.put("error", error);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.Account;
import com.artsoft.model.AccountType;
import com.artsoft.model.AppUser;
import com.artsoft.model.Role;
import com.artsoft.model.State;
import com.artsoft.service.AccountTypeService;
import com.artsoft.service.AppUserService;
import com.artsoft.service.RoleService;
import com.artsoft.service.StateService;
import com.artsoft.util.DateUtil;
import com.artsoft.util.PasswordUtil;


@RestController
@RequestMapping("/signup_control")
@EnableWebMvc
public class SignupController {
	
	private final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
	private final String ACCOUNT_TYPE = "FREE";
	private final String STATE = "ACTIVE";
	private final String IMAGE_PATH = "none";
	
	@Autowired
	AppUserService appUserService;
	
	
	@Autowired
	RoleService roleService;
	
	
	@Autowired
	AccountTypeService accountTypeService;
	
	
	@Autowired
	StateService stateService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Autowired
	UserDetailsService customUserDetailsService;
	
	
	
	@RequestMapping(value = "/signup",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object signup(@RequestBody AppUser user){
		
		Map<String,Object> response = new HashMap<String, Object>();
		int insertedUserId = 0;
		
		// check if email is available
		if(!appUserService.isEmailAvailable(user.getEmail())){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("email");
			error.setErrorMessage("Email '" + user.getEmail() + "' already in use.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		// return with error
		} else{
			user.setCreatedAt(DateUtil.getCurrentTimestamp());								// set the creation date of account
			user.setLastAction(DateUtil.getCurrentTimestamp());					
			user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));				// MD5 encryption of password
			user.setImagePath(IMAGE_PATH);													// the image path will be set in next step of registration or will be added later
			
			/**** SET ROLE ****/
			Role customerRole = roleService.findByName(ROLE_CUSTOMER);						// get the default role : customer
			if(customerRole != null) {
				user.setRole(customerRole);													// assign the role to user
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("role object");
				error.setErrorMessage("Failed to assign role.");
				response.put("error", error);			
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
			
			/**** GET STATE TO ASSIGN TO ACCOUNT ****/
			State state = null;
			state = stateService.findByName(STATE);
			if(state == null){
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("state object");
				error.setErrorMessage("Failed to assign state.");
				response.put("error", error);			
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error				
			}
			
			/**** SET ACCOUNT ****/
			Account account = null;
			AccountType freeAccountType = accountTypeService.findByName(ACCOUNT_TYPE);		// get details about free account
			if(freeAccountType != null){
				account = new Account();
				account.setState(state);                                                    // set state of account: ACTIVE
				account.setAccountType(freeAccountType);									// set the type of account : FREE
				account.setStartDate(DateUtil.getCurrentTimestamp());						// start date: TODAY
				account.setEndDate(null);
				user.setAccount(account);													// set account
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("account type object");
				error.setErrorMessage("Failed to assign account type.");
				response.put("error", error);			
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
			
			
			/**** INSERT USER ****/
			insertedUserId = appUserService.insert(user);
			if(insertedUserId > 0){															// user inserted successfully
			    // after user is inserted , authenticate him
				SecurityContextHolder.clearContext(); 										// clear the security context
				UserDetails userDetails = null;
				try{
					userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
				}catch(UsernameNotFoundException ex){
					 CustomError error = new CustomError();
					 error.setHasError(true);
					 error.setErrorOnField("email");
					 error.setErrorMessage("Incorrect email.");
					 response.put("error", error);
					 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
				}
				
				
				if ( userDetails != null ) {
					Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
					
					response.put("appUserId", insertedUserId);
				}
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("user object");
				error.setErrorMessage("Error registering datails.");
				response.put("error", error);											  
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);   // return with error
			}
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return the response
	}
	
	

	
	
	
}

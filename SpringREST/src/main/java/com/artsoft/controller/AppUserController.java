package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.Account;
import com.artsoft.model.AppUser;
import com.artsoft.service.AccountService;
import com.artsoft.service.AccountTypeService;
import com.artsoft.service.AppUserService;
import com.artsoft.service.RoleService;
import com.artsoft.service.StateService;
import com.artsoft.util.DateUtil;
import com.artsoft.util.PasswordUtil;

@RestController
@RequestMapping("/api/user_control")
@EnableWebMvc
public class AppUserController {
	
	private final String IMAGE_PATH = "none";
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@Autowired
	StateService stateService;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object add(@RequestBody AppUser user){
		
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
			user.setImagePath(IMAGE_PATH);		
				
			/**** INSERT USER ****/
			insertedUserId = appUserService.insert(user);
			if(insertedUserId > 0){															// user inserted successfully
				response.put("appUserId", insertedUserId);
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("user object");
				error.setErrorMessage("Error registering datails.");
				response.put("error", error);											  
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);   // return with error
			}
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object delete(@PathVariable("id") int appUserId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(appUserId != 0){
			AppUser user = appUserService.findById(appUserId);
			if(user != null){
				try{
					appUserService.delete(user);
					response.put("msg", "User deleted.");
				}catch(Exception ex){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("categoryId");
					error.setErrorMessage("Failed to delete user.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("user object");
				error.setErrorMessage("Failed to fetch user.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("categoryId");
			error.setErrorMessage("Incorrect value for user id. '"+ appUserId + "'");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/user/account/status/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object updateUserAccountStatus(@PathVariable("id") int appUserId, @RequestBody Account account){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(appUserId != 0){
			AppUser user = null;
			
			try{
				user = appUserService.findById(appUserId);
			} catch(Exception ex){
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("user object");
				error.setErrorMessage("Failed to fetch user.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
			
			if(user.getAccount().getAccountId() == account.getAccountId()){
				user = null;
				
				try{
					accountService.update(account);
					response.put("msg", "Account status updated.");
				}catch(Exception ex){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("account object");
					error.setErrorMessage("Failed to update account status.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("account object");
				error.setErrorMessage("Incorrect value for account id. '"+ account.getAccountId() + "'");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("categoryId");
			error.setErrorMessage("Incorrect value for user id. '"+ appUserId + "'");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}

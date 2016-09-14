package com.artsoft.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.Address;
import com.artsoft.model.AppUser;
import com.artsoft.service.AddressService;
import com.artsoft.service.AppUserService;


@RestController
@RequestMapping("/api/signup_control")
@EnableWebMvc
public class SignupController {
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	AddressService addressService;
	
	
	
	@RequestMapping(value = "/signup",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object signup(@RequestParam(value = "email") String email,
						 @RequestParam(value = "password") String password,
						 @RequestParam(value = "first-name") String firstName,
						 @RequestParam(value = "last-name") String lastName,
						 @RequestParam(value = "phone") String phone,
						 @RequestParam(value = "county") String county,
						 @RequestParam(value = "city") String city,
						 @RequestParam(value = "description") String description){
		
		Map<String,Object> response = new HashMap();
		int insertedUserId = 0;
		int insertedAddressId = 0;
		AppUser newUser;
		Address address;
		
		AppUser user = appUserService.findByEmail(email);
		if(user == null){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("email");
			error.setErrorMessage("Email " + email + " already in use.");
			response.put("error", error);
		}
		else{
			newUser = new AppUser();
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setPhoneNr(phone);
			newUser.setCreatedAt(new Date(new java.util.Date().getTime()));
			insertedUserId = appUserService.insert(newUser);
			
			if(insertedUserId != 0){												// user inserted successfully
				address = new Address();											// assign address
				address.setAppuser(newUser);
				address.setCounty(county);
				address.setCity(city);
				address.setDescription(description);
				
				insertedAddressId = addressService.insert(address);
				if(insertedAddressId != 0){											// address inserted successfully
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("address object");
					error.setErrorMessage("Error registering address data.");
					response.put("error", error);
				}
				else{
					Set<Address> addresses = new HashSet<Address>();
					addresses.add(address);
					newUser.setAddresses(addresses);								// assign the new address to the address list of the user
				}
				
				
				response.put("user", newUser);										// return the new created user
				
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("appuser object");
				error.setErrorMessage("Error registering data.");
				response.put("error", error);
			}
			
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return the response
	}
	
	
	
	
	
	
	
	
}

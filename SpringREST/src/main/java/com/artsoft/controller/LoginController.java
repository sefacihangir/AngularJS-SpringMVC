package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.AppUser;
import com.artsoft.service.AppUserService;
import com.artsoft.util.PasswordUtil;

@RestController
@RequestMapping("/login_control")
@EnableWebMvc
public class LoginController {

	@Autowired
	AppUserService appUserService;

	@RequestMapping(value = "/login", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object login(@RequestParam(value = "email") String email,
						@RequestParam(value = "password") String password) {

		AppUser user = appUserService.findByEmail(email);
		Map<String, Object> response = new HashMap<String, Object>();

		if (user == null) {
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("email");
			error.setErrorMessage("Invalid email " + email + ".");
			response.put("error", error);
		} else {
			String encryptedInputPassword = PasswordUtil.encryptPassword(password);
			if (user.getPassword().equals(encryptedInputPassword)) {
				response.put("user", user);
			} else {
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("password");
				error.setErrorMessage("Invalid password.");
				response.put("error", error);
			}

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

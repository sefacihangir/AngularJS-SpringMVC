package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/test_control")
@EnableWebMvc
public class TestController {
	
	@RequestMapping(value = "/info",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
	public Object getInfo(Authentication authentication){
		String message = "";
		Map<String,Object> response = new HashMap();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
		     message += authentication.getName()+", You have "+ role;
		}
		
		response.put("response", message);
		return response;
	}
	
}
